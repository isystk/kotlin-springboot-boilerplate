package com.isystk.sample.common.service

import com.isystk.sample.common.dto.PageFactory
import org.springframework.beans.factory.annotation.Autowired

abstract class BaseRepository {
    @Autowired
    protected var pageFactory: PageFactory? = null
}