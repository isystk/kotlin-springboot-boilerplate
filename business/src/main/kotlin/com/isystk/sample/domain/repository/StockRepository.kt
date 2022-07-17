package com.isystk.sample.domain.repository

import com.google.common.collect.Lists
import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.common.service.BaseRepository
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dao.StockDao
import com.isystk.sample.domain.dto.StockCriteria
import com.isystk.sample.domain.dto.StockRepositoryDto
import com.isystk.sample.domain.entity.Stock
import com.isystk.sample.domain.util.DomaUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*
import java.util.stream.Collectors

/**
 * 商品リポジトリ
 */
@Repository
class StockRepository : BaseRepository() {
    @Autowired
    var imageHelper: ImageHelper? = null

    @Autowired
    var stockDao: StockDao? = null

    /**
     * 商品を複数取得します。
     *
     * @param criteria
     * @return
     */
    fun findAll(criteria: StockCriteria?): List<StockRepositoryDto> {
        return convertDto(stockDao!!.findAll(criteria))
    }

    /**
     * 商品を複数取得します。(ページングあり)
     *
     * @param criteria
     * @param pageable
     * @return
     */
    fun findPage(criteria: StockCriteria?, pageable: Pageable): Page<StockRepositoryDto> {
        val options = DomaUtils.createSelectOptions(pageable)
        val stockList = convertDto(stockDao!!.findAll(criteria, options.count(), Collectors.toList()))
        return pageFactory!!.create(stockList, pageable, options.count)
    }

    /**
     * RepositoryDto に変換します。
     *
     * @param stockList
     * @return
     */
    private fun convertDto(stockList: List<Stock>): List<StockRepositoryDto> {

        // stockList を元に、stockDtoList へコピー
        return ObjectMapperUtils
                .mapAll(stockList, StockRepositoryDto::class.java)
    }

    /**
     * 商品を取得します。
     *
     * @param criteria
     * @return
     */
    fun findOne(criteria: StockCriteria): Optional<StockRepositoryDto> {
        val data = stockDao!!.findOne(criteria)
                .orElseThrow { NoDataFoundException(criteria.toString() + "のデータが見つかりません。") }
        return Optional.ofNullable(convertDto(Lists.newArrayList(data))[0])
    }

    /**
     * 商品を取得します。
     *
     * @return
     */
    fun findById(id: BigInteger): StockRepositoryDto {
        val data = stockDao!!.selectById(id)
                .orElseThrow { NoDataFoundException("stock_id=$id のデータが見つかりません。") }
        return convertDto(Lists.newArrayList(data))[0]
    }

    /**
     * 商品を追加します。
     *
     * @param stockDto
     * @return
     */
    fun create(stockDto: StockRepositoryDto): Stock {

        // 画像ファイルをS3にアップロードする
        imageHelper!!.saveFileData(stockDto.stockImageData, "/stocks", stockDto.stockImageName, true)
        val time = DateUtils.now

        // 商品テーブル
        val stock = ObjectMapperUtils.map(stockDto, Stock::class.java)
        stock.imgpath = stockDto.stockImageName
        stock.createdAt = time // 作成日
        stock.updatedAt = time // 更新日
        stock.deleteFlg = false // 削除フラグ
        stock.version = 0L // 楽観ロック改定番号
        stockDao!!.insert(stock)
        return stock
    }

    /**
     * 商品を更新します。
     *
     * @param stockDto
     * @return
     */
    fun update(stockDto: StockRepositoryDto): Stock {
        // 画像ファイルをS3にアップロードする
        imageHelper!!.saveFileData(stockDto.stockImageData, "/stocks", stockDto.stockImageName, true)
        val time = DateUtils.now
        val before = stockDao!!.selectById(stockDto.id)
                .orElseThrow { NoDataFoundException("stock_id=" + stockDto.id + " のデータが見つかりません。") }

        // 商品テーブル
        val stock = ObjectMapperUtils.mapExcludeNull(stockDto, before)
        stock.imgpath = stockDto.stockImageName
        stock.updatedAt = time // 更新日
        stockDao!!.update(stock)
        return stock
    }

    /**
     * 商品を論理削除します。
     *
     * @return
     */
    fun delete(stockId: BigInteger): Stock {
        val stock = stockDao!!.selectById(stockId)
                .orElseThrow { NoDataFoundException("stock_id=$stockId のデータが見つかりません。") }
        val time = DateUtils.now
        stock.updatedAt = time // 削除日
        stock.deleteFlg = true // 削除フラグ
        val updated = stockDao!!.update(stock)
        if (updated < 1) {
            throw NoDataFoundException("stock_id=$stockId は更新できませんでした。")
        }
        return stock
    }
}