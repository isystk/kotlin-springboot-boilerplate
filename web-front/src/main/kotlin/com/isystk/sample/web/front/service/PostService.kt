package com.isystk.sample.web.front.service

import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.common.values.ImageSuffix
import com.isystk.sample.domain.dto.TPostCriteria
import com.isystk.sample.domain.entity.TPost
import com.isystk.sample.domain.entity.TPostImage
import com.isystk.sample.domain.entity.TPostTag
import com.isystk.sample.domain.repository.MPostTagRepository
import com.isystk.sample.domain.repository.TPostRepository
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto
import com.isystk.sample.domain.util.DomaUtils
import com.isystk.sample.solr.dto.SolrPost
import com.isystk.sample.solr.dto.SolrPostCriteria
import com.isystk.sample.solr.repository.SolrPostRepository
import com.isystk.sample.web.front.dto.FrontPostDto
import com.isystk.sample.web.front.dto.FrontPostImageDto
import com.isystk.sample.web.front.dto.FrontPostTagDto
import org.apache.commons.compress.utils.Lists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors

@Service
class PostService : BaseTransactionalService() {
    @Autowired
    var solrPostRepository: SolrPostRepository? = null

    @Autowired
    var postRepository: TPostRepository? = null

    @Autowired
    var imageHelper: ImageHelper? = null

    @Autowired
    var userHelper: UserHelper? = null

    @Autowired
    var mPostTagRepository: MPostTagRepository? = null

    /**
     * Solrの投稿インデックスを取得します。
     *
     * @param criteria
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true) // 読み取りのみの場合は指定する
    fun findSolrAll(criteria: SolrPostCriteria?, pageable: Pageable?): Page<FrontPostDto> {
        Assert.notNull(criteria, "criteria must not be null")

        // TODO ここでページングを設定
        val solrPosts = solrPostRepository!!.findAll()
        val solrPostList: MutableList<FrontPostDto> = Lists.newArrayList()
        for (solrPost in solrPosts) {
            solrPostList.add(convertSolrToFrontPostDto(solrPost))
        }

        // ページングを指定する
        val options = DomaUtils.createSelectOptions(pageable).count()
        return pageFactory.create(solrPostList, pageable, options.count)
    }

    /**
     * Solrの投稿インデックスを取得します。
     *
     * @param postId
     * @return
     */
    @Transactional(readOnly = true) // 読み取りのみの場合は指定する
    fun findDataById(postId: Int?): Optional<FrontPostDto> {
        Assert.notNull(postId, "criteria must not be null")
        val solrPost = solrPostRepository!!.findByPostId(postId)
        return Optional.of(convertSolrToFrontPostDto(solrPost))
    }

    /**
     * @param solrPost
     * @return
     */
    private fun convertSolrToFrontPostDto(solrPost: SolrPost): FrontPostDto {
        // 入力値を詰め替える
        val dto = ObjectMapperUtils.map(solrPost, FrontPostDto::class.java)

        // 画像のパスを設定
        dto.imageList = Optional.ofNullable(solrPost.imageIdList)
                .orElse(Lists.newArrayList())
                .stream()
                .map { imageId: Int? ->
                    val imageDto = FrontPostImageDto()
                    imageDto.imageId = imageId
                    imageDto.imageUrl = imageHelper!!.getUrl(imageId, ImageSuffix.SQUARE.suffix)
                    imageDto
                }
                .collect(Collectors.toList())

        // 投稿タグを設定
        val mPostTagMap = mPostTagRepository!!.findAllSelectMap()
        dto.tagList = Optional.ofNullable(solrPost.tagIdList)
                .orElse(Lists.newArrayList())
                .stream()
                .map { tagId: Int? ->
                    val tagDto = FrontPostTagDto()
                    tagDto.tagId = tagId
                    tagDto.tagName = mPostTagMap[tagId]!!.text
                    tagDto
                }
                .collect(Collectors.toList())
        dto.registTimeYYYYMMDD = DateUtils.format(solrPost.registTime, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        dto.registTimeMMDD = DateUtils.format(solrPost.registTime, DateTimeFormatter.ofPattern("MM/dd"))
        return dto
    }

    /**
     * DBの投稿インデックスを取得します。
     *
     * @param postId
     * @return
     */
    @Transactional(readOnly = true) // 読み取りのみの場合は指定する
    fun findMyDataById(postId: Int?): Optional<FrontPostDto> {
        Assert.notNull(postId, "criteria must not be null")

        // 1件取得する
        val post = postRepository!!.findById(postId)
        if (post.userId != userHelper!!.user.userId) {
            throw NoDataFoundException(
                    "データが見つかりません。post_id=" + postId + " user_id=" + userHelper!!.user.userId)
        }
        return Optional.of(convertTPostToFrontPostDto(post))
    }

    /**
     * @param tPostRepositoryDto
     * @return
     */
    private fun convertTPostToFrontPostDto(tPostRepositoryDto: TPostRepositoryDto): FrontPostDto {
        // 入力値を詰め替える
        val dto = ObjectMapperUtils.map(tPostRepositoryDto, FrontPostDto::class.java)

        // 画像のパスを設定
        dto.imageList = Optional.ofNullable(tPostRepositoryDto.tPostImageList)
                .orElse(Lists.newArrayList())
                .stream()
                .map { tPostImage: TPostImage ->
                    val imageDto = FrontPostImageDto()
                    imageDto.imageId = tPostImage.imageId
                    imageDto.imageUrl = imageHelper!!.getUrl(tPostImage.imageId, ImageSuffix.SQUARE.suffix)
                    imageDto
                }
                .collect(Collectors.toList())

        // 投稿タグを設定
        val mPostTagMap = mPostTagRepository!!.findAllSelectMap()
        dto.tagList = Optional.ofNullable(tPostRepositoryDto.tPostTagList)
                .orElse(Lists.newArrayList())
                .stream()
                .map { tPostTag: TPostTag ->
                    val tagDto = FrontPostTagDto()
                    tagDto.tagId = tPostTag.postTagId
                    tagDto.tagName = mPostTagMap[tPostTag.postTagId]!!.text
                    tagDto
                }
                .collect(Collectors.toList())
        dto.registTimeYYYYMMDD = DateUtils
                .format(tPostRepositoryDto.registTime, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        dto.registTimeMMDD = DateUtils.format(tPostRepositoryDto.registTime, DateTimeFormatter.ofPattern("MM/dd"))
        return dto
    }

    /**
     * 投稿を複数取得します。
     *
     * @param criteria
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true) // 読み取りのみの場合は指定する
    fun findAll(criteria: TPostCriteria?, pageable: Pageable?): Page<FrontPostDto> {
        Assert.notNull(criteria, "criteria must not be null")
        val postDtoPage = postRepository!!.findAll(criteria, pageable)
        val list: MutableList<FrontPostDto> = Lists.newArrayList()
        for (postDto in postDtoPage.data) {
            val dto = ObjectMapperUtils.map(postDto, FrontPostDto::class.java)
            dto.imageList = Optional.ofNullable(postDto.tPostImageList)
                    .orElse(Lists.newArrayList())
                    .stream()
                    .map { tPostImage: TPostImage ->
                        val imageDto = FrontPostImageDto()
                        imageDto.imageId = tPostImage.imageId
                        imageDto.imageUrl = imageHelper!!.getUrl(tPostImage.imageId, ImageSuffix.SQUARE.suffix)
                        imageDto
                    }
                    .collect(Collectors.toList())

            // 投稿タグを設定
            val mPostTagMap = mPostTagRepository!!.findAllSelectMap()
            dto.tagList = Optional.ofNullable(postDto.tPostTagList)
                    .orElse(Lists.newArrayList())
                    .stream()
                    .map { tPostTag: TPostTag ->
                        val tagDto = FrontPostTagDto()
                        tagDto.tagId = tPostTag.postTagId
                        tagDto.tagName = mPostTagMap[tPostTag.postTagId]!!.text
                        tagDto
                    }
                    .collect(Collectors.toList())
            list.add(dto)
        }
        return pageFactory.create(list, postDtoPage, postDtoPage.count)
    }

    /**
     * 投稿を追加します。
     *
     * @param tPostDto
     * @return
     */
    fun create(tPostDto: TPostRepositoryDto?): Int {
        Assert.notNull(tPostDto, "input must not be null")
        val tPost = postRepository!!.create(tPostDto)
        return tPost.postId
    }

    /**
     * 投稿を更新します。
     *
     * @param tPostDto
     * @return
     */
    fun update(tPostDto: TPostRepositoryDto?) {
        Assert.notNull(tPostDto, "input must not be null")
        postRepository!!.update(tPostDto)
    }

    /**
     * 投稿を論理削除します。
     *
     * @return
     */
    fun delete(postId: Int?): TPost {
        Assert.notNull(postId, "postId must not be null")
        return postRepository!!.delete(postId)
    }
}