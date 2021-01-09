package com.isystk.sample.web.front.controller.api.v1.upload

import com.isystk.sample.common.dto.Dto
import lombok.Getter
import lombok.Setter

@Setter
@Getter
class UploadRestDto : Dto {
    var imageId: Int? = null
    var imagePath: Int? = null
}