package com.isystk.sample.web.admin.controller.html.contact

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.web.admin.service.ContactService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.math.BigInteger

@Controller
@RequestMapping(AdminUrl.CONTACTS)
class ContactDetailController : AbstractHtmlController() {
    @Autowired
    var contactService: ContactService? = null
    override fun getFunctionName(): String {
        return "A_CONTACT_DETAIL"
    }

    /**
     * 詳細画面表示
     *
     * @param contactId
     * @param model
     * @return
     */
    @GetMapping("{contactId}")
    fun show(@PathVariable contactId: Long, model: Model): String {
        val contact = contactService!!.findById(contactId)
        model.addAttribute("contact", contact)
        return "modules/contact/detail"
    }

    companion object {
        private val log = LoggerFactory.getLogger(
                ContactDetailController::class.java)
    }
}