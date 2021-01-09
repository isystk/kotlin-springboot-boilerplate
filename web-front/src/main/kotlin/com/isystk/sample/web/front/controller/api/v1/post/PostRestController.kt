package com.isystk.sample.web.front.controller.api.v1.post

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.solr.dto.SolrPostCriteria
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.PageableResource
import com.isystk.sample.web.base.controller.api.resource.PageableResourceImpl
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.dto.FrontPostDto
import com.isystk.sample.web.front.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = [FrontUrl.API_V1_POSTS], produces = [MediaType.APPLICATION_JSON_VALUE])
class PostRestController : AbstractRestController() {
    @Autowired
    var postService: PostService? = null
    override fun getFunctionName(): String {
        return "API_POSTS"
    }

    /**
     * 投稿一覧を複数取得します。
     *
     * @param query
     * @param page
     * @return
     */
    @GetMapping
    fun index(query: PostRestForm?,
              @RequestParam(required = false, defaultValue = "1") page: Int): PageableResource {

        // 入力値からDTOを作成する
        val criteria = ObjectMapperUtils.map(query, SolrPostCriteria::class.java)

        // 10件で区切って取得する
        val posts = postService!!.findSolrAll(criteria, Pageable.DEFAULT)
        val resource: PageableResource = ObjectMapperUtils.map(posts, PageableResourceImpl::class.java)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }

    /**
     * 投稿詳細を取得します。
     *
     * @param postId
     * @return
     */
    @GetMapping(value = ["/{postId}"])
    fun show(@PathVariable postId: Int?): Resource {
        val resource = resourceFactory.create()
        resource.data = Arrays.asList(postService!!.findDataById(postId).orElse(FrontPostDto()))
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }
}