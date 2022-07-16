package com.isystk.sample.web.front.controller.api.v1.like

import com.google.common.collect.Maps
import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.base.controller.api.resource.ResourceImpl
import com.isystk.sample.web.front.service.LikeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.math.BigInteger

@RestController
@RequestMapping(path = [FrontUrl.API_V1_LIKES], produces = [MediaType.APPLICATION_JSON_VALUE])
class LikeController : AbstractRestController() {
    @Autowired
    var likeService: LikeService? = null
    override fun getFunctionName(): String {
        return "API_LIKES"
    }

    /**
     * お気に入りの一覧を取得します。
     *
     * @return
     */
    @GetMapping
    fun index(): Resource {
        val likes = likeService?.likes
        val data = hashMapOf(
                "data" to likes
        )
        val resource: Resource = ObjectMapperUtils.map<ResourceImpl, Map<*, *>>(data, ResourceImpl::class.java)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }

    /**
     * お気に入りを追加します。
     *
     * @return
     */
    @PostMapping("/add")
    fun addLike(@RequestParam("id") stockId: BigInteger): Resource {
        val likes = likeService!!.addLike(stockId)
        val data = hashMapOf(
                "data" to likes
        )
        val resource: Resource = ObjectMapperUtils.map<ResourceImpl, Map<*, *>>(data, ResourceImpl::class.java)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }

    /**
     * お気に入りを削除します。
     *
     * @return
     */
    @PostMapping("/remove")
    fun removeLike(@RequestParam("id") stockId: BigInteger): Resource {
        val likes = likeService!!.removeLike(stockId)
        val data = hashMapOf(
                "data" to likes
        )
        val resource: Resource = ObjectMapperUtils.map<ResourceImpl, Map<*, *>>(data, ResourceImpl::class.java)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }
}