package com.isystk.sample.web.base.controller.html

import java.io.Serializable

abstract class BaseForm : Serializable {
    // 改定番号
    var version: Int? = null
}