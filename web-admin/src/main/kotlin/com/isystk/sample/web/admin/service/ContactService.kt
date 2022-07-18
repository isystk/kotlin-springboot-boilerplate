package com.isystk.sample.web.admin.service

import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.domain.dto.ContactFormCriteria
import com.isystk.sample.domain.dto.ContactFormImageRepositoryDto
import com.isystk.sample.domain.dto.ContactFormRepositoryDto
import com.isystk.sample.domain.entity.ContactForm
import com.isystk.sample.domain.repository.ContactFormRepository
import com.isystk.sample.web.admin.dto.ContactSearchConditionDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.math.BigInteger
import java.time.LocalTime

@Service
class ContactService : BaseTransactionalService() {
    @Autowired
    var imageHelper: ImageHelper? = null

    @Autowired
    var contactRepository: ContactFormRepository? = null

    /**
     * お問い合わせを複数取得します。
     *
     * @param dto
     * @return
     */
    fun findAll(dto: ContactSearchConditionDto): List<ContactFormRepositoryDto> {
        return contactRepository!!.findAll(dtoToCriteria(dto))
    }

    /**
     * お問い合わせを複数取得します。(ページングあり)
     *
     * @param dto
     * @param pageable
     * @return
     */
    fun findPage(dto: ContactSearchConditionDto, pageable: Pageable): Page<ContactFormRepositoryDto> {
        return contactRepository!!.findPage(dtoToCriteria(dto), pageable)
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun dtoToCriteria(
            dto: ContactSearchConditionDto): ContactFormCriteria {
        // 入力値を詰め替える
        val criteria = ContactFormCriteria()
        criteria.yourNameLike = dto.userName
        criteria.createdAtGe = dto.createdAtFrom?.atStartOfDay()
        criteria.createdAtLe = dto.createdAtTo?.atTime(LocalTime.MAX)
        criteria.isDeleteFlgFalse = true
        criteria.orderBy = "order by updated_at desc"
        return criteria
    }

    /**
     * お問い合わせを取得します。
     *
     * @param contactId
     * @return
     */
    fun findById(contactId: BigInteger): ContactFormRepositoryDto {
        val contact = contactRepository!!.findById(contactId)
        val imageList = contact.imageList?.map {
           e: ContactFormImageRepositoryDto ->
                val imageData = imageHelper!!.getImageData("/contacts", e.fileName)
                e.contactImageData = imageData
                e.contactImageName = e.fileName
                e
        } ?: emptyList()
        contact.imageList = imageList
        return contact
    }

    /**
     * お問い合わせを追加します。
     *
     * @param contactDto
     * @return
     */
    fun create(contactDto: ContactFormRepositoryDto): ContactForm {
        Assert.notNull(contactDto, "input must not be null")
        return contactRepository!!.create(contactDto)
    }

    /**
     * お問い合わせを更新します。
     *
     * @param contactDto
     * @return
     */
    fun update(contactDto: ContactFormRepositoryDto): ContactForm {
        Assert.notNull(contactDto, "input must not be null")
        return contactRepository!!.update(contactDto)
    }

    /**
     * お問い合わせを論理削除します。
     *
     * @return
     */
    fun delete(id: BigInteger): ContactForm {
        Assert.notNull(id, "id must not be null")
        return contactRepository!!.delete(id)
    }
}