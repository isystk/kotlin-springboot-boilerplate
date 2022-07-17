package com.isystk.sample.common.service

import com.isystk.sample.common.dto.PageFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Throwable::class])
abstract class BaseTransactionalService : BaseService() {
    @Autowired
    protected var pageFactory: PageFactory? = null
}