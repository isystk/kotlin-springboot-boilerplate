package com.isystk.sample.web.front.dto

import com.isystk.sample.common.dto.Dto
import lombok.Getter
import lombok.Setter

/**
 * 投稿フロント表示用 投稿画像DTO
 */
@Getter
@Setter
class FrontPostTagDto : Dto {
    var tagId: Int? = null
    var tagName: String? = null
}