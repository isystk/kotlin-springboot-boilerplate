package com.isystk.sample.domain.dto

import com.isystk.sample.domain.entity.ContactForm

class ContactFormRepositoryDto : ContactForm() {
    var imageList: List<ContactFormImageRepositoryDto>? = null
}