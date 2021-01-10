package com.isystk.sample.web.admin.service

import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.domain.entity.TPost
import com.isystk.sample.domain.repository.TPostRepository
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert

@Service
class PostService : BaseTransactionalService() {
    @Autowired
    var postRepository: TPostRepository? = null

    /**
     * 投稿を追加します。
     *
     * @param tPostDto
     * @return
     */
    fun create(tPostDto: TPostRepositoryDto?): TPost {
        Assert.notNull(tPostDto, "input must not be null")
        return postRepository!!.create(tPostDto)
    }

    /**
     * 投稿を更新します。
     *
     * @param tPostDto
     * @return
     */
    fun update(tPostDto: TPostRepositoryDto?): TPost {
        Assert.notNull(tPostDto, "input must not be null")
        return postRepository!!.update(tPostDto)
    }

    /**
     * 投稿を論理削除します。
     *
     * @return
     */
    fun delete(id: Int?): TPost {
        Assert.notNull(id, "id must not be null")
        return postRepository!!.delete(id)
    }
}