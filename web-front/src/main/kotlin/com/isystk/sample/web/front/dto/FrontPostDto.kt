package com.isystk.sample.web.front.dto

import com.isystk.sample.common.dto.Dto
import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime

/**
 * 投稿フロント表示用DTO
 */
@Getter
@Setter
class FrontPostDto : Dto {
    var postId: Int? = null
    var userId: Int? = null
    var title: String? = null
    var text: String? = null
    var registTime: LocalDateTime? = null
    var registTimeYYYYMMDD: String? = null
    var registTimeMMDD: String? = null
    var imageList: List<FrontPostImageDto>? = null
    var tagList: List<FrontPostTagDto>? = null
}