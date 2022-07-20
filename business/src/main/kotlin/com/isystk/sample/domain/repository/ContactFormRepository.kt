package com.isystk.sample.domain.repository

import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.common.service.BaseRepository
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dao.ContactFormDao
import com.isystk.sample.domain.dao.ContactFormImageDao
import com.isystk.sample.domain.dto.ContactFormCriteria
import com.isystk.sample.domain.dto.ContactFormImageCriteria
import com.isystk.sample.domain.dto.ContactFormImageRepositoryDto
import com.isystk.sample.domain.dto.ContactFormRepositoryDto
import com.isystk.sample.domain.entity.ContactForm
import com.isystk.sample.domain.entity.ContactFormImage
import com.isystk.sample.domain.util.DomaUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*
import java.util.stream.Collectors

/**
 * お問い合わせリポジトリ
 */
@Repository
class ContactFormRepository : BaseRepository() {
    @Autowired
    var imageHelper: ImageHelper? = null

    @Autowired
    var contactFormDao: ContactFormDao? = null

    @Autowired
    var contactFormImageDao: ContactFormImageDao? = null

    /**
     * お問い合わせを複数取得します。
     *
     * @param criteria
     * @return
     */
    fun findAll(criteria: ContactFormCriteria): List<ContactFormRepositoryDto> {
        val options = DomaUtils.createSelectOptions(1, Int.MAX_VALUE)
        return convertDto(contactFormDao!!.findAll(criteria, options, Collectors.toList()))
    }

    /**
     * お問い合わせを複数取得します。(ページングあり)
     *
     * @param criteria
     * @param pageable
     * @return
     */
    fun findPage(criteria: ContactFormCriteria, pageable: Pageable): Page<ContactFormRepositoryDto> {
        val options = DomaUtils.createSelectOptions(pageable)
        val contactFormList = convertDto(contactFormDao!!.findAll(criteria, options.count(), Collectors.toList()))
        return pageFactory!!.create(contactFormList, pageable, options.count)
    }

    /**
     * RepositoryDto に変換します。
     *
     * @param contactFormList
     * @return
     */
    private fun convertDto(contactFormList: List<ContactForm>): List<ContactFormRepositoryDto> {

        // contactFormListからcontactFormIdのListを抽出
        val contactFormIdList = contactFormList.map { e: ContactForm -> e.id }

        // contactFormId をkeyとした、contactFormImageListのMapを生成
        val contactFormImageCriteria = ContactFormImageCriteria()
        contactFormImageCriteria.contactFormIdIn = contactFormIdList
        val contactFormImageMap = contactFormImageDao!!.findAll(contactFormImageCriteria)
                .map { e: ContactFormImage -> ObjectMapperUtils.map(e, ContactFormImageRepositoryDto::class.java) }
                .groupBy({ it.contactFormId }, { it })

        // contactFormList を元に、contactFormDtoList へコピー
        return ObjectMapperUtils
                .mapAll(contactFormList, ContactFormRepositoryDto::class.java)
                .map { e: ContactFormRepositoryDto ->
                    e.imageList = contactFormImageMap[e.id]
                    e
                }
    }

    /**
     * お問い合わせを取得します。
     *
     * @param criteria
     * @return
     */
    fun findOne(criteria: ContactFormCriteria): ContactFormRepositoryDto?{
        val data = contactFormDao!!.findOne(criteria)
            ?: throw NoDataFoundException(criteria.toString() + "のデータが見つかりません。")
        return convertDto(mutableListOf(data))[0]
    }

    /**
     * お問い合わせを取得します。
     *
     * @return
     */
    fun findById(id: BigInteger): ContactFormRepositoryDto? {
        val data = contactFormDao!!.selectById(id)
            ?: throw NoDataFoundException("contactForm_id=$id のデータが見つかりません。")
        return convertDto(mutableListOf(data))[0]
    }

    /**
     * お問い合わせを追加します。
     *
     * @param contactFormDto
     * @return
     */
    fun create(contactFormDto: ContactFormRepositoryDto): ContactForm {

        // 画像ファイルをS3にアップロードする
        contactFormDto.imageList
                .forEach { e: ContactFormImageRepositoryDto -> imageHelper!!.saveFileData(e.contactImageData, "/contacts", e.contactImageName, true) }
        val time = DateUtils.now

        // お問い合わせテーブル
        val contactForm = ObjectMapperUtils.map(contactFormDto, ContactForm::class.java)
        contactForm.createdAt = time // 作成日
        contactForm.updatedAt = time // 更新日
        contactForm.deleteFlg = false // 削除フラグ
        contactForm.version = 0L // 楽観ロック改定番号
        contactFormDao!!.insert(contactForm)

        // お問い合わせ画像テーブル
        contactFormDto.imageList
                .forEach { e: ContactFormImageRepositoryDto ->
                    val contactFormImage = ContactFormImage()
                    contactFormImage.contactFormId = contactForm.id
                    contactFormImage.fileName = e.contactImageName
                    contactFormImage.createdAt = time // 作成日
                    contactFormImage.updatedAt = time // 更新日
                    contactFormImage.deleteFlg = false // 削除フラグ
                    contactFormImage.version = 0L // 楽観ロック改定番号
                    contactFormImageDao!!.insert(contactFormImage)
                }
        return contactForm
    }

    /**
     * お問い合わせを更新します。
     *
     * @param contactFormDto
     * @return
     */
    fun update(contactFormDto: ContactFormRepositoryDto): ContactForm {
        // 画像ファイルをS3にアップロードする
        contactFormDto.imageList
                .forEach { e: ContactFormImageRepositoryDto -> imageHelper!!.saveFileData(e.contactImageData, "/contacts", e.contactImageName, true) }
        val time = DateUtils.now
        val before = contactFormDao!!.selectById(contactFormDto.id)
            ?: throw NoDataFoundException("contactForm_id=" + contactFormDto.id + " のデータが見つかりません。")

        // お問い合わせテーブル
        val contactForm = ObjectMapperUtils.mapExcludeNull(contactFormDto, before)
        contactForm.updatedAt = time // 更新日
        contactFormDao!!.update(contactForm)

        // お問い合わせ画像テーブル (Delete → Insert)
        val criteria = ContactFormImageCriteria()
        criteria.contactFormIdEq = contactFormDto.id
        val contactFormImageList = contactFormImageDao!!.findAll(criteria)
        contactFormImageList.forEach { e: ContactFormImage -> contactFormImageDao!!.delete(e) }
        contactFormDto.imageList
                .forEach { e: ContactFormImageRepositoryDto ->
                    val contactFormImage = ContactFormImage()
                    contactFormImage.contactFormId = contactForm.id
                    contactFormImage.fileName = e.contactImageName
                    contactFormImage.createdAt = time // 作成日
                    contactFormImage.updatedAt = time // 更新日
                    contactFormImage.deleteFlg = false // 削除フラグ
                    contactFormImage.version = 0L // 楽観ロック改定番号
                    contactFormImageDao!!.insert(contactFormImage)
                }
        return contactForm
    }

    /**
     * お問い合わせを論理削除します。
     *
     * @return
     */
    fun delete(contactFormId: BigInteger): ContactForm {
        val contactForm = contactFormDao!!.selectById(contactFormId)
            ?: throw NoDataFoundException("contactForm_id=$contactFormId のデータが見つかりません。")
        val time = DateUtils.now
        run {
            contactForm.updatedAt = time // 削除日
            contactForm.deleteFlg = true // 削除フラグ
            val updated = contactFormDao!!.update(contactForm)
            if (updated < 1) {
                throw NoDataFoundException("contactForm_id=$contactFormId は更新できませんでした。")
            }
        }
        val criteria = ContactFormImageCriteria()
        criteria.contactFormIdEq = contactFormId
        val contactFormImageList = contactFormImageDao!!.findAll(criteria)
        contactFormImageList.forEach { e: ContactFormImage ->
            e.updatedAt = time // 削除日
            e.deleteFlg = true // 削除フラグ
            val updated = contactFormImageDao!!.update(e)
            if (updated < 1) {
                throw NoDataFoundException("contactForm_id=$contactFormId は更新できませんでした。")
            }
        }
        return contactForm
    }
}