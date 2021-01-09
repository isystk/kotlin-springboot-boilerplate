package com.isystk.sample.web.front.controller.api.v1.upload

import com.isystk.sample.web.base.controller.html.BaseForm
import lombok.Getter
import lombok.Setter
import org.springframework.web.multipart.MultipartFile

@Setter
@Getter
class UploadRestForm : BaseForm() {
    var imageId: Int? = null
    var imageFile: MultipartFile? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}