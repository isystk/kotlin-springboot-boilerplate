package com.isystk.sample.domain.repository

import com.isystk.sample.common.dto.StripePaymentDto
import com.isystk.sample.common.dto.mail.MailStockPaymentComplete
import com.isystk.sample.common.exception.ErrorMessagesException
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.exception.SystemException
import com.isystk.sample.common.helper.SendMailHelper
import com.isystk.sample.common.service.BaseRepository
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.common.values.MailTemplateDiv
import com.isystk.sample.domain.dao.CartDao
import com.isystk.sample.domain.dao.OrderHistoryDao
import com.isystk.sample.domain.dao.StockDao
import com.isystk.sample.domain.dto.CartCriteria
import com.isystk.sample.domain.dto.CartRepositoryDto
import com.isystk.sample.domain.dto.StockCriteria
import com.isystk.sample.domain.entity.Cart
import com.isystk.sample.domain.entity.OrderHistory
import com.isystk.sample.domain.entity.User
import com.stripe.Stripe
import com.stripe.exception.StripeException
import com.stripe.model.PaymentIntent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

/**
 * マイカートリポジトリ
 */
@Repository
class CartRepository : BaseRepository() {
    @Value("\${spring.mail.properties.mail.from}")
    var fromAddress: String? = null

    @Value("\${stripe.apiSecret}")
    var apiSecret: String? = null

    @Autowired
    var stockDao: StockDao? = null

    @Autowired
    var cartDao: CartDao? = null

    @Autowired
    var orderHistoryDao: OrderHistoryDao? = null

    @Autowired
    var mailTemplateRepository: MailTemplateRepository? = null

    @Autowired
    var sendMailHelper: SendMailHelper? = null

    /**
     * ユーザに紐づくカート情報を複数取得します。
     *
     * @param userId
     * @return
     */
    fun findCart(userId: Long?): List<CartRepositoryDto> {
        val criteria = CartCriteria()
        criteria.userIdEq = userId
        return convertCartDto(cartDao!!.findAll(criteria))
    }

    /**
     * RepositoryDto に変換します。
     *
     * @param cartList
     * @return
     */
    private fun convertCartDto(cartList: List<Cart>): List<CartRepositoryDto> {
        // cartListからstockIdのListを抽出
        val stockIdList = cartList.map { e: Cart -> e.stockId }

        // stockId をkeyとした、stockListのMapを生成
        val stockCriteria = StockCriteria()
        stockCriteria.idIn = stockIdList
        val stockMap = stockDao!!.findAll(stockCriteria)
                .groupBy({ it.id }, {it})

        // cartList を元に、cartDtoList へコピー
        return ObjectMapperUtils.mapAll(cartList, CartRepositoryDto::class.java)
                .map { e: CartRepositoryDto ->
                    e.stock = stockMap[e.stockId]!![0]
                    e
                }
    }

    /**
     * ユーザに紐づくカートに商品を追加します。
     *
     * @param userId
     * @param stockId
     * @return
     */
    fun addCart(userId: Long?, stockId: Long?): List<CartRepositoryDto> {
        val time = DateUtils.now

        // 商品テーブル
        val cart = Cart()
        cart.userId = userId
        cart.stockId = stockId
        cart.createdAt = time // 作成日
        cart.updatedAt = time // 更新日
        cart.deleteFlg = false // 削除フラグ
        cart.version = 0L // 楽観ロック改定番号
        cartDao!!.insert(cart)
        return findCart(userId)
    }

    /**
     * ユーザに紐づくカートから商品を削除します。
     *
     * @param userId
     * @param cartId
     * @return
     */
    fun removeCart(userId: Long, cartId: Long): List<CartRepositoryDto> {
        val cart = cartDao!!.selectById(cartId)
             ?: throw
                NoDataFoundException(
                        "cartId=" + cartId + "のデータが見つかりません。")
        cartDao!!.delete(cart)
        return findCart(userId)
    }

    /**
     * Stripeの支払情報を生成します。
     * @param user
     * @param amount
     * @param email
     */
    fun createPayment(user: User, amount: Int, email: String): StripePaymentDto {
        val cartList = findCart(user.id)

        // stockId をkeyとした、cartListのMapを生成
        val cartMap = cartList
                .groupBy({ it.stockId }, {it})

        // ユニークなstockIdを取得
        val stockIdList = cartList.map { e: CartRepositoryDto -> e.stockId }.distinct()

        // 発注履歴に追加する。
        stockIdList.forEach { stockId ->
            val stockCartList = cartMap[stockId]!!
            val cartStock = stockCartList[0].stock
            val quantity = stockCartList.size
            if (cartStock != null) {
                if (cartStock.quantity!! < quantity) {
                    // 在庫が不足している場合はエラーとする
                    throw ErrorMessagesException("在庫が不足しています。stockId:$stockId")
                }
            }
        }
        val dto = StripePaymentDto()
        Stripe.apiKey = apiSecret
        val metadata: MutableMap<String, Any> = mutableMapOf()
        metadata["username"] = email
        val params: MutableMap<String, Any> = mutableMapOf()
        params["amount"] = amount
        params["description"] = "LaraEC"
        params["currency"] = "jpy"
        params["metadata"] = metadata
        try {
            val paymentIntent = PaymentIntent.create(params)
            dto.clientSecret = paymentIntent.clientSecret
        } catch (e: StripeException) {
            throw SystemException(e)
        }
        return dto
    }

    /**
     * 決算処理完了後の後処理をします。
     * @param user
     * @return
     */
    fun checkout(user: User): Boolean {
        val time = DateUtils.now
        val cartList = findCart(user.id)

        // stockId をkeyとした、cartListのMapを生成
        val cartMap = cartList
                .groupBy({it.stockId}, {it} )

        // ユニークなstockIdを取得
        val stockIdList = cartList.map { e: CartRepositoryDto -> e.stockId }.distinct()

        // 発注履歴に追加する。
        stockIdList.forEach { stockId ->
            val stockCartList = cartMap[stockId]!!
            val cartStock = stockCartList[0].stock
            val quantity = stockCartList.size
            if (cartStock != null) {
                if (cartStock.quantity!! < quantity) {
                    // 在庫が不足している場合はエラーとする
                    throw ErrorMessagesException("在庫が不足しています。stockId:$stockId")
                }
            }

            // 在庫を減らす
            val stock = stockDao!!.selectById(cartStock?.id!!) ?: throw
                 NoDataFoundException(
                        "stockId=" + cartStock.id + "のデータが見つかりません。")
            stock.quantity = stock.quantity!! - quantity
            stock.updatedAt = time // 更新日
            stockDao!!.update(stock)

            // 注文履歴テーブル
            val orderHistory = OrderHistory()
            orderHistory.stockId = stockId
            orderHistory.userId = user.id
            if (cartStock != null) {
                orderHistory.price = cartStock.price
            }
            orderHistory.quantity = quantity.toLong()
            orderHistory.createdAt = time // 作成日
            orderHistory.updatedAt = time // 更新日
            orderHistory.deleteFlg = false // 削除フラグ
            orderHistory.version = 0L // 楽観ロック改定番号
            orderHistoryDao!!.insert(orderHistory)
        }

        // カートから商品を削除
        cartList.forEach { e: CartRepositoryDto -> removeCart(user.id!!, e.id!!) }

        // ユーザ宛に購入完了メール送信
        val amount = cartList.map { e: CartRepositoryDto -> e.stock?.price!! }.sum()
        val mailTemplate = mailTemplateRepository!!.getMailTemplate(MailTemplateDiv.STOCK_PAYMENT_COMPLETE)
        val subject = mailTemplate!!.title
        val templateBody = mailTemplate.text
        val dto = MailStockPaymentComplete()
        dto.userName = user.name
        dto.amount = amount
        val objects: MutableMap<String, Any> = mutableMapOf()
        objects["dto"] = dto
        val body = sendMailHelper!!.getMailBody(templateBody, objects)
        sendMailHelper!!.sendMail(fromAddress, user.email, subject, body)
        return true
    }

}