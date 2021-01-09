package com.isystk.sample.web.admin.controller.html.post;

import static org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined.DARK_GREEN;
import static org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined.WHITE;

import com.isystk.sample.domain.entity.TPost;
import com.isystk.sample.web.base.view.ExcelView;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class PostExcel implements ExcelView.Callback {

  @Override
  public void buildExcelWorkbook(Map<String, Object> model, Collection<?> data, Workbook workbook) {

    // シートを作成する
    Sheet sheet = workbook.createSheet("投稿一覧");
    sheet.setDefaultColumnWidth(30);

    // フォント
    Font font = workbook.createFont();
    font.setFontName("メイリオ");
    font.setBold(true);
    font.setColor(WHITE.getIndex());

    // ヘッダーのスタイル
    CellStyle style = workbook.createCellStyle();
    style.setFillForegroundColor(DARK_GREEN.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setFont(font);

    Row header = sheet.createRow(0);
    header.createCell(0).setCellValue("投稿ID");
    header.getCell(0).setCellStyle(style);
    header.createCell(1).setCellValue("ユーザーID");
    header.getCell(1).setCellStyle(style);
    header.createCell(2).setCellValue("タイトル");
    header.getCell(2).setCellStyle(style);

    // 明細
    @SuppressWarnings("unchecked")
    List<TPost> posts = (List<TPost>) data;

    int count = 1;
    for (TPost post : posts) {
      Row userRow = sheet.createRow(count++);
      userRow.createCell(0).setCellValue(post.getPostId());
      userRow.createCell(1).setCellValue(post.getUserId());
      userRow.createCell(2).setCellValue(post.getTitle());
    }
  }
}
