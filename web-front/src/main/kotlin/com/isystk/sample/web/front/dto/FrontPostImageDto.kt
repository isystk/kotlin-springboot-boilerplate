package com.isystk.sample.web.front.dto

import com.isystk.sample.common.dto.Dto
import lombok.Getter
import lombok.Setter

/**
 * 投稿フロント表示用 投稿画像DTO
 */
@Getter
@Setter
class FrontPostImageDto : Dto {
    var imageId: Int? = null
    var imageUrl: String? = null
}