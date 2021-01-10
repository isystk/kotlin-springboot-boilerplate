package com.isystk.sample.batch.controller.html.post

import com.isystk.sample.domain.entity.TPost
import com.isystk.sample.web.base.view.ExcelView
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.Workbook

class PostExcel : ExcelView.Callback {
    override fun buildExcelWorkbook(model: Map<String, Any>, data: Collection<*>, workbook: Workbook) {

        // シートを作成する
        val sheet = workbook.createSheet("投稿一覧")
        sheet.defaultColumnWidth = 30

        // フォント
        val font = workbook.createFont()
        font.fontName = "メイリオ"
        font.bold = true
        font.color = HSSFColorPredefined.WHITE.index

        // ヘッダーのスタイル
        val style = workbook.createCellStyle()
        style.fillForegroundColor = HSSFColorPredefined.DARK_GREEN.index
        style.fillPattern = FillPatternType.SOLID_FOREGROUND
        style.setFont(font)
        val header = sheet.createRow(0)
        header.createCell(0).setCellValue("投稿ID")
        header.getCell(0).cellStyle = style
        header.createCell(1).setCellValue("ユーザーID")
        header.getCell(1).cellStyle = style
        header.createCell(2).setCellValue("タイトル")
        header.getCell(2).cellStyle = style

        // 明細
        val posts = data as List<TPost>
        var count = 1
        for (post in posts) {
            val userRow = sheet.createRow(count++)
            userRow.createCell(0).setCellValue(post.postId.toDouble())
            userRow.createCell(1).setCellValue(post.userId.toDouble())
            userRow.createCell(2).setCellValue(post.title)
        }
    }
}