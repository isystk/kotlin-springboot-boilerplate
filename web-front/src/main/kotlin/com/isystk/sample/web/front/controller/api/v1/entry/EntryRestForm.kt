package com.isystk.sample.web.front.controller.api.v1.entry

import com.isystk.sample.common.validator.annotation.PhoneNumber
import com.isystk.sample.common.validator.annotation.ZenKana
import com.isystk.sample.common.validator.annotation.ZipCode
import lombok.Getter
import lombok.Setter
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDate
import javax.validation.constraints.*

class EntryRestForm : Serializable {
    /**
     * 姓
     */
    var familyName: @NotBlank @Size(max = 100) String? = null

    /**
     * 名
     */
    var name: @NotBlank @Size(max = 100) String? = null

    /**
     * 姓（カナ）
     */
    @ZenKana
    var familyNameKana: @NotBlank @Size(max = 100) String? = null

    /**
     * 名（カナ）
     */
    @ZenKana
    var nameKana: @NotBlank @Size(max = 100) String? = null

    /**
     * メールアドレス
     */
    var email: @NotBlank @Email @Size(max = 100) String? = null

    /**
     * パスワード
     */
    var password: @NotBlank @Size(max = 100) String? = null

    /**
     * パスワード(確認用)
     */
    var passwordConf: @NotBlank @Size(max = 100) String? = null

    /**
     * 性別
     */
    var sex: @NotNull @Digits(integer = 11, fraction = 0) Int? = null

    /**
     * 郵便番号
     */
    @ZipCode
    var zip: @Size(max = 11) String? = null

    /**
     * 都道府県
     */
    var prefectureId: @Digits(integer = 11, fraction = 0) String? = null

    /**
     * 市区町村
     */
    var area: @Size(max = 100) String? = null

    /**
     * 町名番地
     */
    var address: @Size(max = 100) String? = null

    /**
     * 建物名
     */
    var building: @Size(max = 100) String? = null

    /**
     * 電話番号
     */
    @PhoneNumber
    var tel: String? = null

    /**
     * 生年月日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthday: LocalDate? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}