package com.isystk.sample.domain.repository

import com.google.common.collect.Lists
import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.service.BaseRepository
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dao.OrderHistoryDao
import com.isystk.sample.domain.dao.StockDao
import com.isystk.sample.domain.dao.UserDao
import com.isystk.sample.domain.dto.OrderHistoryCriteria
import com.isystk.sample.domain.dto.OrderHistoryRepositoryDto
import com.isystk.sample.domain.dto.StockCriteria
import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.entity.OrderHistory
import com.isystk.sample.domain.entity.Stock
import com.isystk.sample.domain.entity.User
import com.isystk.sample.domain.util.DomaUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

/**
 * 注文履歴リポジトリ
 */
@Repository
class OrderHistoryRepository : BaseRepository() {
    @Autowired
    var orderHistoryDao: OrderHistoryDao? = null

    @Autowired
    var stockDao: StockDao? = null

    @Autowired
    var userDao: UserDao? = null

    /**
     * 注文履歴を複数取得します。
     *
     * @param criteria
     * @return
     */
    fun findAll(criteria: OrderHistoryCriteria?): List<OrderHistoryRepositoryDto> {
        val options = DomaUtils.createSelectOptions(1, Int.MAX_VALUE)
        return convertDto(orderHistoryDao!!.findAll(criteria, options, Collectors.toList()))
    }

    /**
     * 注文履歴を複数取得します。(ページングあり)
     *
     * @param criteria
     * @param pageable
     * @return
     */
    fun findPage(criteria: OrderHistoryCriteria?, pageable: Pageable): Page<OrderHistoryRepositoryDto> {
        val options = DomaUtils.createSelectOptions(pageable)
        val orderHistoryList = convertDto(orderHistoryDao!!.findAll(criteria, options.count(), Collectors.toList()))
        return pageFactory!!.create(orderHistoryList, pageable, options.count)
    }

    /**
     * RepositoryDto に変換します。
     *
     * @param orderHistoryList
     * @return
     */
    private fun convertDto(orderHistoryList: List<OrderHistory>): List<OrderHistoryRepositoryDto> {

        // orderHistoryListからstockIdのListを抽出
        val stockIdList = orderHistoryList.stream().map { e: OrderHistory -> e.stockId }
                .collect(Collectors.toList())

        // stockId をkeyとした、stockListのMapを生成
        val stockCriteria = StockCriteria()
        stockCriteria.idIn = stockIdList
        val stockMap = stockDao!!.findAll(stockCriteria)
                .stream().collect(Collectors.groupingBy(Function { obj: Stock -> obj.id }))

        // orderHistoryListからuserIdのListを抽出
        val userIdList = orderHistoryList.stream().map { e: OrderHistory -> e.userId }
                .collect(Collectors.toList())

        // userList をkeyとした、userListのMapを生成
        val userCriteria = UserCriteria()
        userCriteria.idIn = userIdList
        val userMap = userDao!!.findAll(userCriteria)
                .stream().collect(Collectors.groupingBy(Function { obj: User -> obj.id }))

        // orderHistoryList を元に、orderHistoryDtoList へコピー
        val orderHistoryDtoList = ObjectMapperUtils
                .mapAll(orderHistoryList, OrderHistoryRepositoryDto::class.java)
        orderHistoryDtoList
                .stream()
                .forEach { e: OrderHistoryRepositoryDto ->
                    e.stock = stockMap[e.stockId]!![0]
                    e.user = userMap[e.userId]!![0]
                }
        return orderHistoryDtoList
    }

    /**
     * 注文履歴を取得します。
     *
     * @param criteria
     * @return
     */
    fun findOne(criteria: OrderHistoryCriteria): Optional<OrderHistoryRepositoryDto> {
        val data = orderHistoryDao!!.findOne(criteria)
                .orElseThrow { NoDataFoundException(criteria.toString() + "のデータが見つかりません。") }
        return Optional.ofNullable(convertDto(Lists.newArrayList(data))[0])
    }

    /**
     * 注文履歴を取得します。
     *
     * @return
     */
    fun findById(id: BigInteger): OrderHistoryRepositoryDto {
        val data = orderHistoryDao!!.selectById(id)
                .orElseThrow { NoDataFoundException("orderHistory_id=$id のデータが見つかりません。") }
        return convertDto(Lists.newArrayList(data))[0]
    }

    /**
     * RepositoryDto に変換します。
     *
     * @param orderHistory
     * @return
     */
    private fun convertDto(orderHistory: OrderHistory): OrderHistoryRepositoryDto {
        // orderHistory を元に、OrderHistoryRepositoryDto へコピー
        val dto = ObjectMapperUtils.map(orderHistory, OrderHistoryRepositoryDto::class.java)

        // stock
        val stockCriteria = StockCriteria()
        stockCriteria.idEq = orderHistory.stockId
        val stock = stockDao!!.findOne(stockCriteria).orElse(Stock())
        dto.stock = stock

        // user
        val userCriteria = UserCriteria()
        userCriteria.idEq = orderHistory.userId
        val user = userDao!!.findOne(userCriteria).orElse(User())
        dto.user = user
        return dto
    }

    /**
     * 注文履歴を追加します。
     *
     * @param orderHistoryDto
     * @return
     */
    fun create(orderHistoryDto: OrderHistoryRepositoryDto): OrderHistory {
        val time = DateUtils.now

        // 注文履歴テーブル
        val orderHistory = ObjectMapperUtils.map(orderHistoryDto, OrderHistory::class.java)
        orderHistory.createdAt = time // 作成日
        orderHistory.updatedAt = time // 更新日
        orderHistory.deleteFlg = false // 削除フラグ
        orderHistory.version = 0L // 楽観ロック改定番号
        orderHistoryDao!!.insert(orderHistory)
        return orderHistory
    }

    /**
     * 注文履歴を更新します。
     *
     * @param orderHistoryDto
     * @return
     */
    fun update(orderHistoryDto: OrderHistoryRepositoryDto): OrderHistory {
        val time = DateUtils.now
        val before = orderHistoryDao!!.selectById(orderHistoryDto.id)
                .orElseThrow { NoDataFoundException("orderHistory_id=" + orderHistoryDto.id + " のデータが見つかりません。") }

        // 注文履歴テーブル
        val orderHistory = ObjectMapperUtils.mapExcludeNull(orderHistoryDto, before)
        orderHistory.updatedAt = time // 更新日
        orderHistoryDao!!.update(orderHistory)
        return orderHistory
    }

    /**
     * 注文履歴を論理削除します。
     *
     * @return
     */
    fun delete(orderHistoryId: BigInteger): OrderHistory {
        val orderHistory = orderHistoryDao!!.selectById(orderHistoryId)
                .orElseThrow { NoDataFoundException("orderHistory_id=$orderHistoryId のデータが見つかりません。") }
        val time = DateUtils.now
        orderHistory.updatedAt = time // 削除日
        orderHistory.deleteFlg = true // 削除フラグ
        val updated = orderHistoryDao!!.update(orderHistory)
        if (updated < 1) {
            throw NoDataFoundException("orderHistory_id=$orderHistoryId は更新できませんでした。")
        }
        return orderHistory
    }
}