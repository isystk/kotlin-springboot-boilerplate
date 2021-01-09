package com.isystk.sample.web.front.controller.api.v1.member.post

import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.validator.AbstractValidator
import com.isystk.sample.domain.dao.TPostDao
import com.isystk.sample.domain.dto.TPostCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 投稿 入力チェック
 */
@Component
class MemberPostEditRestValidator : AbstractValidator<MemberPostEditRestForm?>() {
    @Autowired
    var tPostDao: TPostDao? = null

    @Autowired
    var userHelper: UserHelper? = null

    override fun doValidate(form: MemberPostEditRestForm?, errors: Errors?) {
        // 自分の投稿かどうか
        val criteria = TPostCriteria()
        criteria.postIdEq = form!!.postId
        criteria.userIdEq = userHelper!!.user.userId
        tPostDao!!.findOne(criteria)
                .orElseThrow {
                    NoDataFoundException(
                            "post_id=" + form!!.postId + " user_id=" + userHelper!!.user.userId
                                    + " のデータが見つかりません。")
                }
    }
}