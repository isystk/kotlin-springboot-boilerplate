package com.isystk.sample.web.base.view

import com.fasterxml.jackson.dataformat.csv.CsvGenerator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.isystk.sample.common.util.EncodeUtils
import com.isystk.sample.common.util.ValidateUtils
import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.view.AbstractView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * CSVビュー
 */
class CsvView(clazz: Class<*>, data: Collection<*>, filename: String) : AbstractView() {
    protected var clazz: Class<*>
    protected var data: Collection<*>
    protected var filename: String
    protected var columns: List<String?>? = null

    /**
     * コンストラクタ
     *
     * @param clazz
     * @param data
     * @param filename
     */
    init {
        contentType = "application/octet-stream; charset=Windows-31J;"
        this.clazz = clazz
        this.data = data
        this.filename = filename
    }

    override fun generatesDownloadContent(): Boolean {
        return true
    }

    @Throws(Exception::class)
    override fun renderMergedOutputModel(model: Map<String, Any>,
                                         request: HttpServletRequest,
                                         response: HttpServletResponse) {

        // ファイル名に日本語を含めても文字化けしないようにUTF-8にエンコードする
        val encodedFilename = EncodeUtils.encodeUtf8(filename)
        val contentDisposition = String.format("attachment; filename*=UTF-8''%s", encodedFilename)
        response.setHeader(HttpHeaders.CONTENT_TYPE, contentType)
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)

        // CSVヘッダをオブジェクトから作成する
        var schema = csvMapper.schemaFor(clazz).withHeader()
        if (ValidateUtils.isNotEmpty(columns)) {
            // カラムが指定された場合は、スキーマを再構築する
            val builder = schema.rebuild().clearColumns()
            for (column in columns!!) {
                builder.addColumn(column)
            }
            schema = builder.build()
        }
        response.writer.use { writer -> csvMapper.writer(schema).writeValue(writer, data) }
    }

    companion object {
        protected val csvMapper = createCsvMapper()

        /**
         * CSVマッパーを生成する。
         *
         * @return
         */
        fun createCsvMapper(): CsvMapper {
            val mapper = CsvMapper()
            mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true)
            mapper.findAndRegisterModules()
            return mapper
        }
    }
}