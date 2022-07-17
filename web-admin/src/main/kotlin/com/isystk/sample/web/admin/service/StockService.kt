package com.isystk.sample.web.admin.service

import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.domain.dto.StockCriteria
import com.isystk.sample.domain.dto.StockRepositoryDto
import com.isystk.sample.domain.entity.Stock
import com.isystk.sample.domain.repository.StockRepository
import com.isystk.sample.web.admin.dto.StockSearchConditionDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.math.BigInteger
import java.time.LocalTime

@Service
class StockService : BaseTransactionalService() {
    @Autowired
    var imageHelper: ImageHelper? = null

    @Autowired
    var stockRepository: StockRepository? = null

    /**
     * 商品を複数取得します。
     *
     * @param dto
     * @return
     */
    fun findAll(dto: StockSearchConditionDto): List<StockRepositoryDto> {
        return stockRepository!!.findAll(dtoToCriteria(dto))
    }

    /**
     * 商品を複数取得します。(ページングあり)
     *
     * @param dto
     * @param pageable
     * @return
     */
    fun findPage(dto: StockSearchConditionDto, pageable: Pageable): Page<StockRepositoryDto> {
        return stockRepository!!.findPage(dtoToCriteria(dto), pageable)
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun dtoToCriteria(
            dto: StockSearchConditionDto): StockCriteria {
        // 入力値を詰め替える
        val criteria = StockCriteria()
        criteria.idEq = dto.stockId
        criteria.nameLike = dto.name
        if (dto.createdAtFrom != null) {
            criteria.createdAtGe = dto.createdAtFrom!!.atStartOfDay()
        }
        if (dto.createdAtTo != null) {
            criteria.createdAtLe = dto.createdAtTo!!.atTime(LocalTime.MAX)
        }
        criteria.isDeleteFlgFalse = true
        criteria.orderBy = "order by updated_at desc"
        return criteria
    }

    /**
     * 商品を取得します。
     *
     * @param stockId
     * @return
     */
    fun findById(stockId: BigInteger): StockRepositoryDto {
        // 1件取得する
        val stock = stockRepository!!.findById(stockId)
        val imageData = imageHelper!!.getImageData("/stocks", stock.imgpath)
        stock.stockImageData = imageData
        stock.stockImageName = stock.imgpath
        return stock
    }

    /**
     * 商品を追加します。
     *
     * @param stockDto
     * @return
     */
    fun create(stockDto: StockRepositoryDto): Stock {
        Assert.notNull(stockDto, "input must not be null")
        return stockRepository!!.create(stockDto)
    }

    /**
     * 商品を更新します。
     *
     * @param stockDto
     * @return
     */
    fun update(stockDto: StockRepositoryDto): Stock {
        Assert.notNull(stockDto, "input must not be null")
        return stockRepository!!.update(stockDto)
    }

    /**
     * 商品を論理削除します。
     *
     * @return
     */
    fun delete(id: BigInteger): Stock {
        Assert.notNull(id, "id must not be null")
        return stockRepository!!.delete(id)
    }
}