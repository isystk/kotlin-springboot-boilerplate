package com.isystk.sample.web.base.view

import com.isystk.sample.common.util.EncodeUtils
import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.engine.util.JRLoader
import net.sf.jasperreports.engine.xml.JRXmlLoader
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.view.AbstractView
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * PDFビュー
 */
class PdfView(report: String, data: Collection<*>, filename: String) : AbstractView() {
    protected var report: String
    protected var data: Collection<*>
    protected var filename: String

    /**
     * コンストラクタ
     *
     * @param report
     * @param data
     * @param filename
     */
    init {
        contentType = "application/pdf"
        this.report = report
        this.data = data
        this.filename = filename
    }

    @Throws(Exception::class)
    override fun renderMergedOutputModel(model: Map<String, Any>, request: HttpServletRequest,
                                         response: HttpServletResponse) {

        // IEの場合はContent-Lengthヘッダが指定されていないとダウンロードが失敗するので、
        // サイズを取得するための一時的なバイト配列ストリームにコンテンツを書き出すようにする
        val baos = createTemporaryOutputStream()

        // 帳票レイアウト
        val report = loadReport()

        // データの設定
        val dataSource = JRBeanCollectionDataSource(data)
        val print = JasperFillManager.fillReport(report, model, dataSource)
        val exporter = JRPdfExporter()
        exporter.setExporterInput(SimpleExporterInput(print))
        exporter.exporterOutput = SimpleOutputStreamExporterOutput(baos)
        exporter.exportReport()

        // ファイル名に日本語を含めても文字化けしないようにUTF-8にエンコードする
        val encodedFilename = EncodeUtils.encodeUtf8(filename)
        val contentDisposition = String.format("attachment; filename*=UTF-8''%s", encodedFilename)
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
        writeToResponse(response, baos)
    }

    /**
     * 帳票レイアウトを読み込む
     *
     * @return
     */
    protected fun loadReport(): JasperReport {
        val resource = ClassPathResource(report)
        try {
            val fileName = resource.filename
            if (fileName!!.endsWith(".jasper")) {
                resource.inputStream.use { `is` -> return JRLoader.loadObject(`is`) as JasperReport }
            } else if (fileName.endsWith(".jrxml")) {
                resource.inputStream.use { `is` ->
                    val design = JRXmlLoader.load(`is`)
                    return JasperCompileManager.compileReport(design)
                }
            } else {
                throw IllegalArgumentException(
                        ".jasper または .jrxml の帳票を指定してください。 [$fileName] must end in either ")
            }
        } catch (e: IOException) {
            throw IllegalArgumentException("failed to load report. $resource", e)
        } catch (e: JRException) {
            throw IllegalArgumentException("failed to parse report. $resource", e)
        }
    }
}