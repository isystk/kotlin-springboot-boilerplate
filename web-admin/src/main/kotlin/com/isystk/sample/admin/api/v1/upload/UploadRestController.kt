package com.isystk.sample.batch.api.v1.upload

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.Const
import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.base.view.FileDownloadView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*

/**
 * ファイルアップロードのコントローラー
 */
@RestController
@RequestMapping(path = [AdminUrl.API_V1_FILEUPLOAD], produces = [MediaType.APPLICATION_JSON_VALUE])
class UploadRestController : AbstractRestController() {
    @Autowired
    var imageHelper: ImageHelper? = null
    override fun getFunctionName(): String {
        return "API_FILEUPLOAD"
    }

    /**
     * ファイル内容表示
     *
     * @param imageId
     * @return
     */
    @GetMapping("/{imageId}")
    @ResponseBody
    fun serveFile(@PathVariable imageId: Int): ModelAndView {
        // ファイルを読み込む
        val resource = imageHelper!!.loadFile(imageId)

        // レスポンスを設定する
        val view = FileDownloadView(resource)
        view.setAttachment(false)
        view.setFilename(imageId.toString() + "." + Const.IMAGE_EXTENSION)
        return ModelAndView(view)
    }

    /**
     * ファイルダウンロード
     *
     * @param imageId
     * @return
     */
    @GetMapping("/download/{imageId}")
    @ResponseBody
    fun downloadFile(@PathVariable imageId: Int): ModelAndView {
        // ファイルを読み込む
        val resource = imageHelper!!.loadFile(imageId)

        // レスポンスを設定する
        val view = FileDownloadView(resource)
        view.setFilename(imageId.toString() + "." + Const.IMAGE_EXTENSION)
        return ModelAndView(view)
    }

    /**
     * 画像アップロードを行う
     *
     * @param form
     * @param model
     * @return
     */
    @PostMapping("/image")
    fun post(@ModelAttribute("uploadRestForm") form: UploadRestForm, model: Model?): Resource {

        // ファイルを保存する
        val dto = imageHelper!!.saveFile(form.imageFile)
        val resource = resourceFactory.create()
        resource.data = Arrays.asList(dto)
        resource.message = getMessage("uploadfiles.upload.success")
        return resource
    }
}