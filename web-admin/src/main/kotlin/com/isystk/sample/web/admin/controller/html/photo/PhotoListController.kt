package com.isystk.sample.web.admin.controller.html.photo

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.web.admin.service.PhotoService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
@RequestMapping(AdminUrl.PHOTOS)
class PhotoListController : AbstractHtmlController() {
    @Autowired
    var photoService: PhotoService? = null

    @Autowired
    var photoListFormValidator: PhotoListFormValidator? = null
    override fun getFunctionName(): String {
        return "A_PHOTO_LIST"
    }

    @ModelAttribute
    fun initForm(): PhotoListForm {
        return PhotoListForm()
    }

    @InitBinder
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(photoListFormValidator)
    }

    /**
     * 一覧画面表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun index(@ModelAttribute form: @Valid PhotoListForm?, br: BindingResult,
              sessionStatus: SessionStatus?, attributes: RedirectAttributes, model: Model): String {
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return "modules/photo/list"
        }

        // 10件区切りで取得する
        val pages = photoService!!.findAll(form?.name)

        // 画面に検索結果を渡す
        model.addAttribute("pages", pages)
        return "modules/photo/list"
    }

    /**
     * 削除処理
     *
     * @param imageName
     * @return
     */
    @DeleteMapping
    fun delete(@RequestParam(value = "name") imageName: String): String {
        photoService!!.delete(imageName)
        return "redirect:/photos"
    }

}