package com.isystk.sample.domain.dto

import com.isystk.sample.domain.entity.ContactFormImage

class ContactFormImageRepositoryDto : ContactFormImage() {
    var contactImageName: String? = null
    var contactImageData: String? = null
}