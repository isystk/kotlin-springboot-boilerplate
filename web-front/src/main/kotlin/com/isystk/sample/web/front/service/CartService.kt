package com.isystk.sample.web.front.service

import com.isystk.sample.common.dto.StripePaymentDto
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.domain.dto.CartRepositoryDto
import com.isystk.sample.domain.entity.User
import com.isystk.sample.domain.repository.CartRepository
import com.isystk.sample.web.front.dto.CartSearchResultDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.math.BigInteger
import java.util.function.ToIntFunction
import java.util.stream.Collectors

@Service
class CartService : BaseTransactionalService() {
    @Autowired
    var cartRepository: CartRepository? = null

    @Autowired
    var userHelper: UserHelper? = null

    /**
     * ログイン中のユーザのカート情報を取得します。
     *
     * @return
     */
    fun searchCart(): CartSearchResultDto {
        val user = userHelper!!.user
        Assert.notNull(user, "user must not be null")
        val cartList = cartRepository!!.findCart(user.id)
        val dto = CartSearchResultDto()
        dto.cartList = cartList
        dto.count = cartList.size
        dto.sum = cartList.stream().map { e: CartRepositoryDto -> e.stock.price }
                .collect(Collectors.summingInt(ToIntFunction { obj: Int -> obj }))
        dto.username = user.name
        return dto
    }

    /**
     * ログイン中のユーザのカートに商品を追加します。
     *
     * @return
     */
    fun addCart(stockId: BigInteger?): CartSearchResultDto {
        val user = userHelper!!.user
        Assert.notNull(user, "user must not be null")
        val cartList = cartRepository!!.addCart(user.id, stockId)
        return convertCartSearchResultDto(user, cartList)
    }

    private fun convertCartSearchResultDto(user: User, cartList: List<CartRepositoryDto>): CartSearchResultDto {
        val dto = CartSearchResultDto()
        dto.cartList = cartList
        dto.count = cartList.size
        dto.sum = cartList.stream().map { e: CartRepositoryDto -> e.stock.price }
                .collect(Collectors.summingInt(ToIntFunction { obj: Int -> obj }))
        dto.username = user.name
        return dto
    }

    /**
     * ログイン中のユーザのカートに商品を追加します。
     *
     * @return
     */
    fun removeCart(cartId: BigInteger): CartSearchResultDto {
        val user = userHelper!!.user
        Assert.notNull(user, "user must not be null")
        val cartList = cartRepository!!.removeCart(user.id, cartId)
        return convertCartSearchResultDto(user, cartList)
    }

    /**
     * Stripeの支払情報を生成します。
     *
     * @return
     */
    fun createPayment(amount: Int, email: String): StripePaymentDto {
        val user = userHelper!!.user
        return cartRepository!!.createPayment(user, amount, email)
    }

    /**
     * 決算処理完了後の後処理をします。
     *
     * @return
     */
    fun checkout(): Boolean {
        val user = userHelper!!.user
        return cartRepository!!.checkout(user)
    }
}