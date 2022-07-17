package com.isystk.sample.web.admin.controller.html.stock

import com.isystk.sample.domain.entity.Stock
import com.isystk.sample.web.base.view.ExcelView
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.Workbook

class StockExcel : ExcelView.Callback {
    override fun buildExcelWorkbook(model: Map<String, Any>?, data: Collection<*>?, workbook: Workbook?) {

        // シートを作成する
        val sheet = workbook!!.createSheet("商品一覧")
        sheet.defaultColumnWidth = 30

        // フォント
        val font = workbook!!.createFont()
        font.fontName = "メイリオ"
        font.bold = true
        font.color = HSSFColorPredefined.WHITE.index

        // ヘッダーのスタイル
        val style = workbook.createCellStyle()
        style.fillForegroundColor = HSSFColorPredefined.DARK_GREEN.index
        style.fillPattern = FillPatternType.SOLID_FOREGROUND
        style.setFont(font)
        val header = sheet.createRow(0)
        header.createCell(0).setCellValue("ID")
        header.getCell(0).cellStyle = style
        header.createCell(1).setCellValue("商品名")
        header.getCell(1).cellStyle = style
        header.createCell(2).setCellValue("価格")
        header.getCell(2).cellStyle = style

        // 明細
        val stocks = data as List<Stock>
        var count = 1
        for (stock in stocks) {
            val userRow = sheet.createRow(count++)
            userRow.createCell(0).setCellValue(stock.id.toString())
            userRow.createCell(1).setCellValue(stock.name)
            userRow.createCell(2).setCellValue(stock.price.toDouble())
        }
    }
}