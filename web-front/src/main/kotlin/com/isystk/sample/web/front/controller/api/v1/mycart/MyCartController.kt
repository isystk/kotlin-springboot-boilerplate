package com.isystk.sample.web.front.controller.api.v1.mycart

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.service.CartService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger
import java.util.*

@RestController
@RequestMapping(path = [FrontUrl.API_V1_MYCARTS], produces = [MediaType.APPLICATION_JSON_VALUE])
class MyCartController : AbstractRestController() {
    @Autowired
    var cartService: CartService? = null
    override fun getFunctionName(): String {
        return "API_MYCARTS"
    }

    /**
     * マイカートを取得します。
     *
     * @return
     */
    @PostMapping
    fun index(): Resource {
        val resource = resourceFactory!!.create()
        resource.data = Arrays.asList(cartService!!.searchCart())
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }

    /**
     * マイカートに商品を追加します。
     *
     * @return
     */
    @PostMapping("/add")
    fun addCart(@RequestParam("stock_id") stockId: BigInteger?): Resource {
        val resource = resourceFactory!!.create()
        resource.data = Arrays.asList(cartService!!.addCart(stockId))
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }

    /**
     * マイカートから商品を削除します。
     *
     * @return
     */
    @PostMapping("/remove")
    fun removeCart(@RequestParam("cart_id") cartId: BigInteger?): Resource {
        val resource = resourceFactory!!.create()
        resource.data = Arrays.asList(cartService!!.removeCart(cartId))
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }

    /**
     * Stripeの支払情報を生成します。
     *
     * @return
     */
    @PostMapping("/payment")
    fun payment(@RequestParam("amount") amount: Int?, @RequestParam("username") email: String?): Resource {
        val dto = cartService!!.createPayment(amount, email)
        val resource = resourceFactory!!.create()
        resource.data = Arrays.asList(dto)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }

    /**
     * 決算処理完了後の後処理をします。
     *
     * @return
     */
    @PostMapping("/checkout")
    fun checkout(): Resource {
        val result = cartService!!.checkout()
        val resource = resourceFactory!!.create()
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = result
        return resource
    }
}