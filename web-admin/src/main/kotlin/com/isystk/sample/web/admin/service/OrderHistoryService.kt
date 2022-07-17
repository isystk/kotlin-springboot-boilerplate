package com.isystk.sample.web.admin.service

import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.StringUtils
import com.isystk.sample.domain.dao.StockDao
import com.isystk.sample.domain.dao.UserDao
import com.isystk.sample.domain.dto.OrderHistoryCriteria
import com.isystk.sample.domain.dto.OrderHistoryRepositoryDto
import com.isystk.sample.domain.dto.StockCriteria
import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.entity.Stock
import com.isystk.sample.domain.entity.User
import com.isystk.sample.domain.repository.OrderHistoryRepository
import com.isystk.sample.web.admin.dto.OrderHistorySearchConditionDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.time.LocalTime
import java.util.stream.Collectors

@Service
class OrderHistoryService : BaseTransactionalService() {
    @Autowired
    val orderHistoryRepository: OrderHistoryRepository? = null

    @Autowired
    val userDao: UserDao? = null

    @Autowired
    val stockDao: StockDao? = null

    /**
     * 商品を複数取得します。
     *
     * @param dto
     * @return
     */
    fun findAll(dto: OrderHistorySearchConditionDto): List<OrderHistoryRepositoryDto> {
        return orderHistoryRepository!!.findAll(dtoToCriteria(dto))
    }

    /**
     * 商品を複数取得します。(ページングあり)
     *
     * @param dto
     * @param pageable
     * @return
     */
    fun findPage(dto: OrderHistorySearchConditionDto, pageable: Pageable): Page<OrderHistoryRepositoryDto> {
        return orderHistoryRepository!!.findPage(dtoToCriteria(dto), pageable)
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun dtoToCriteria(
            dto: OrderHistorySearchConditionDto): OrderHistoryCriteria {
        // 入力値を詰め替える
        val criteria = OrderHistoryCriteria()

        // 注文者での絞り込み
        if (!StringUtils.isBlankOrSpace(dto.userName)) {
            val userCriteria = UserCriteria()
            userCriteria.nameLike = dto.userName
            val userList = userDao!!.findAll(userCriteria)
            // userListからuserIdのListを抽出
            val userIdList = userList.stream().map { e: User -> e.id }
                    .collect(Collectors.toList())
            criteria.userIdIn = userIdList
        }

        // 商品名での絞り込み
        if (!StringUtils.isBlankOrSpace(dto.stockName)) {
            val stockCriteria = StockCriteria()
            stockCriteria.nameLike = dto.stockName
            val stockList = stockDao!!.findAll(stockCriteria)
            // stockListからstockIdのListを抽出
            val stockIdList = stockList.stream().map { e: Stock -> e.id }
                    .collect(Collectors.toList())
            criteria.stockIdIn = stockIdList
        }
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
    fun findById(stockId: BigInteger): OrderHistoryRepositoryDto {
        return orderHistoryRepository!!.findById(stockId)
    }
}