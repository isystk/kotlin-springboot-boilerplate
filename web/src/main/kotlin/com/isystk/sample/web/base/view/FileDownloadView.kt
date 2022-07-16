package com.isystk.sample.web.base.view

import org.apache.tika.Tika
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.servlet.view.AbstractView
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * FileDownloadビュー
 */
class FileDownloadView @JvmOverloads constructor(private val resource: Resource, chunkSize: Int = 256) : AbstractView() {
    private val chunkSize = chunkSize
    private var isAttachment = true
    protected var filename: String? = null

    @Throws(Exception::class)
    override fun renderMergedOutputModel(model: Map<String, Any>,
                                         request: HttpServletRequest,
                                         response: HttpServletResponse) {
        try {
            resource.inputStream.use { inputStream ->
                response.outputStream.use { outputStream ->
                    val file = resource.file
                    val detectedContentType = TIKA.detect(file)
                    val mediaType = MediaType.parseMediaType(detectedContentType)
                    val inlineOrAttachment = if (isAttachment) "attachment" else "inline"
                    val contentDisposition = String.format("%s; filename=\"%s\"", inlineOrAttachment, filename)
                    response.setHeader(HttpHeaders.CONTENT_TYPE, mediaType.toString())
                    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    val buffer = ByteArray(chunkSize)
                    var length: Int
                    while (inputStream.read(buffer).also { length = it } > 0) {
                        outputStream.write(buffer, 0, length)
                    }
                    outputStream.flush()
                }
            }
        } catch (e: IOException) {
            throw IllegalArgumentException(e)
        }
    }

    fun setAttachment(isAttachment: Boolean) {
        this.isAttachment = isAttachment
    }

    companion object {
        protected val TIKA = Tika()
    }
}