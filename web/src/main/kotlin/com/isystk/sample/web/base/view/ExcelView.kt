package com.isystk.sample.web.base.view

import com.isystk.sample.common.util.EncodeUtils
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.view.document.AbstractXlsxView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Excelビュー
 */
class ExcelView() : AbstractXlsxView() {
    protected var filename: String? = null
    protected var data: Collection<*>? = null
    protected var callback: Callback? = null

    /**
     * コンストラクタ
     */
    init {
        contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=Windows-31J;"
    }

    /**
     * コンストラクタ
     *
     * @param callback
     * @param data
     * @param filename
     */
    constructor(callback: Callback?, data: Collection<*>?, filename: String?) : this() {
        this.callback = callback
        this.data = data
        this.filename = filename
    }

    @Throws(Exception::class)
    override fun buildExcelDocument(model: Map<String, Any>, workbook: Workbook,
                                    request: HttpServletRequest,
                                    response: HttpServletResponse) {

        // ファイル名に日本語を含めても文字化けしないようにUTF-8にエンコードする
        val encodedFilename = EncodeUtils.encodeUtf8(filename)
        val contentDisposition = String.format("attachment; filename*=UTF-8''%s", encodedFilename)
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)

        // Excelブックを構築する
        callback!!.buildExcelWorkbook(model, data, workbook)
    }

    interface Callback {
        /**
         * Excelブックを構築します。
         *
         * @param model
         * @param data
         * @param workbook
         */
        fun buildExcelWorkbook(model: Map<String, Any>?, data: Collection<*>?, workbook: Workbook?)
    }
}