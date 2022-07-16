package com.isystk.sample.web.admin.service

import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.StringUtils
import com.isystk.sample.common.values.ImageType
import com.isystk.sample.web.admin.dto.PhotoSearchResultDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class PhotoService : BaseTransactionalService() {
    @Autowired
    var imageHelper: ImageHelper? = null

    /**
     * 写真を複数取得します。
     *
     * @param name
     * @return
     */
    fun findAll(name: String?): List<PhotoSearchResultDto> {
        val stockImages = imageHelper!!.getImageList("")
        return stockImages.stream()
                .filter { e: String ->
                    if (StringUtils.isBlankOrSpace(name)) {
                        return@filter true
                    }
                    0 <= e.indexOf(name!!)
                }
                .map { e: String ->
                    val dto = PhotoSearchResultDto()
                    if (0 <= e.indexOf("stock")) {
                        dto.imageType = ImageType.STOCK
                    } else if (0 <= e.indexOf("contact")) {
                        dto.imageType = ImageType.CONTACT
                    } else {
                        dto.imageType = ImageType.UNKNOWN
                    }
                    dto.imageName = e
                    dto
                }.collect(Collectors.toList())
    }

    /**
     * 写真を削除します。
     * @param imageName
     */
    fun delete(imageName: String?) {
        imageHelper!!.removeFile("/", imageName)
    }
}