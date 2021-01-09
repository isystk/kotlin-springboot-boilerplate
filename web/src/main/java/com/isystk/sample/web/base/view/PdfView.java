package com.isystk.sample.web.base.view;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import com.isystk.sample.common.util.EncodeUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.AbstractView;

/**
 * PDFビュー
 */
public class PdfView extends AbstractView {

  protected String report;

  protected Collection<?> data;

  protected String filename;

  /**
   * コンストラクタ
   *
   * @param report
   * @param data
   * @param filename
   */
  public PdfView(String report, Collection<?> data, String filename) {
    super();
    this.setContentType("application/pdf");
    this.report = report;
    this.data = data;
    this.filename = filename;
  }

  @Override
  protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    // IEの場合はContent-Lengthヘッダが指定されていないとダウンロードが失敗するので、
    // サイズを取得するための一時的なバイト配列ストリームにコンテンツを書き出すようにする
    ByteArrayOutputStream baos = createTemporaryOutputStream();

    // 帳票レイアウト
    JasperReport report = loadReport();

    // データの設定
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(this.data);
    JasperPrint print = JasperFillManager.fillReport(report, model, dataSource);

    JRPdfExporter exporter = new JRPdfExporter();
    exporter.setExporterInput(new SimpleExporterInput(print));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
    exporter.exportReport();

    // ファイル名に日本語を含めても文字化けしないようにUTF-8にエンコードする
    String encodedFilename = EncodeUtils.encodeUtf8(filename);
    String contentDisposition = String.format("attachment; filename*=UTF-8''%s", encodedFilename);
    response.setHeader(CONTENT_DISPOSITION, contentDisposition);

    writeToResponse(response, baos);
  }

  /**
   * 帳票レイアウトを読み込む
   *
   * @return
   */
  protected final JasperReport loadReport() {
    ClassPathResource resource = new ClassPathResource(this.report);

    try {
      String fileName = resource.getFilename();
      if (fileName.endsWith(".jasper")) {
        try (InputStream is = resource.getInputStream()) {
          return (JasperReport) JRLoader.loadObject(is);
        }
      } else if (fileName.endsWith(".jrxml")) {
        try (InputStream is = resource.getInputStream()) {
          JasperDesign design = JRXmlLoader.load(is);
          return JasperCompileManager.compileReport(design);
        }
      } else {
        throw new IllegalArgumentException(
            ".jasper または .jrxml の帳票を指定してください。 [" + fileName + "] must end in either ");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("failed to load report. " + resource, e);
    } catch (JRException e) {
      throw new IllegalArgumentException("failed to parse report. " + resource, e);
    }
  }
}
