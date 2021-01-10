package com.isystk.sample.batch.service

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.isystk.sample.common.dto.PageFactory
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dao.MPostTagDao
import com.isystk.sample.domain.dto.MPostTagCriteria
import com.isystk.sample.domain.dto.TPostCriteria
import com.isystk.sample.domain.entity.MPostTag
import com.isystk.sample.domain.entity.TPostImage
import com.isystk.sample.domain.entity.TPostTag
import com.isystk.sample.domain.repository.TPostRepository
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto
import com.isystk.sample.solr.dto.SolrPost
import com.isystk.sample.solr.repository.SolrPostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class SolrPostService : BaseTransactionalService() {
    @Autowired
    var solrPostRepository: SolrPostRepository? = null

    @Autowired
    var postRepository: TPostRepository? = null

    @Autowired
    var mPostTagDao: MPostTagDao? = null

    @Autowired
    var pageFactory: PageFactory? = null

    /**
     * Solrの投稿インデックスを更新します。
     *
     * @return
     */
    fun refresh() {

        // 有効な投稿を全件取得する
        val criteria = TPostCriteria()
        criteria.isDeleteFlgFalse = true
        val postPage = postRepository!!.findAll(criteria, Pageable.NO_LIMIT)
        if (postPage.count == 0L) {
            // 投稿データが0件の場合は何もしない
            return
        }
        val mPostTagCriteria = MPostTagCriteria()
        mPostTagCriteria.isDeleteFlgFalse = true
        val mPostTagList = mPostTagDao!!.findAll(mPostTagCriteria)
        val tagNameMap: MutableMap<Int, String> = Maps.newHashMap()
        mPostTagList.stream().forEach { mPostTag: MPostTag -> tagNameMap[mPostTag.postTagId] = mPostTag.name }
        val solrPostList: MutableList<SolrPost> = Lists.newArrayList()
        postPage.data
                .stream()
                .forEach { tPostDto: TPostRepositoryDto ->
                    val solrPost = ObjectMapperUtils.map(tPostDto, SolrPost::class.java)

                    // 投稿画像データを詰める
                    solrPost.imageIdList = Optional.ofNullable(tPostDto.tPostImageList)
                            .map { list: List<TPostImage> ->
                                list.stream()
                                        .map { tPostImage: TPostImage -> tPostImage.imageId }
                                        .collect(Collectors.toList())
                            }
                            .orElse(Lists.newArrayList())

                    // 投稿タグIDデータを詰める
                    val tagIdList = Optional.ofNullable(tPostDto.tPostTagList)
                            .map { list: List<TPostTag> ->
                                list.stream()
                                        .map { tPostTag: TPostTag -> tPostTag.postTagId }
                                        .collect(Collectors.toList())
                            }
                            .orElse(Lists.newArrayList())
                    solrPost.tagIdList = tagIdList

                    // 投稿タグ名称データを詰める
                    solrPost.tagNameList = tagIdList
                            .stream()
                            .map { tagId: Int? -> tagNameMap[tagId] }
                            .collect(Collectors.toList())
                    solrPostList.add(solrPost)
                }

        // Solrをすべて削除
        solrPostRepository!!.deleteAll()

        // Solrに保存
        solrPostRepository!!.saveAll(solrPostList)
    }
}