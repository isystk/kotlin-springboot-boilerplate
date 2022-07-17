package com.isystk.sample.common

/**
 * 定数定義
 */
interface Const {
    companion object {
        /**
         * ---- Image ----
         */
        const val IMAGE_EXTENSION = "jpg"

        /**
         * ---- MDC ----
         */
        const val MDC_LOGIN_USER_ID = "LOGIN_USER_ID"
        const val MDC_FUNCTION_NAME = "FUNCTION_NAME"

        /**
         * ---- Message ----
         */
        const val GLOBAL_SUCCESS_MESSAGE = "GlobalSuccessMessage"
        const val GLOBAL_DANGER_MESSAGE = "GlobalDangerMessage"
        const val VALIDATION_ERROR = "ValidationError"
        const val OPTIMISTIC_LOCKING_FAILURE_ERROR = "OptimisticLockingFailureError"
        const val DOUBLE_SUBMIT_ERROR = "DoubleSubmitError"
        const val NO_DATA_FOUND_ERROR = "NoDataFoundError"
        const val UNEXPECTED_ERROR = "UnexpectedError"
        const val MESSAGE_DELETED = "Deleted"
        const val MESSAGE_SUCCESS = "Success"

        /**
         * ---- View ----
         */
        const val ERROR_VIEW = "/error/500.html"
        const val NOTFOUND_VIEW = "/error/404.html"
        const val FORBIDDEN_VIEW = "/error/403.html"

        /**
         * ---- ViewComponents ----
         */
        const val MAV_CONST = "Const"
        const val MAV_ERRORS = "errors"
        const val MAV_PULLDOWN_OPTION = "PulldownOption"
        const val MAV_LOGIN_USER = "LoginUser"
        //    String MAV_CODE_CATEGORIES = "CodeCategories";
        /**
         * ---- URLs ----
         */
        const val ERROR_URL = "/error"
        const val FORBIDDEN_URL = "/forbidden"
        const val LOGIN_URL = "/login"
        const val LOGIN_PROCESSING_URL = "/auth/authenticate"
        const val LOGIN_SUCCESS_URL = "/auth/loginSuccess"
        const val LOGIN_FAILURE_URL = "/auth/loginFailure"
        const val LOGIN_TIMEOUT_URL = "/auth/loginTimeout"
        const val RESET_PASSWORD_URL = "/auth/resetPassword"
        const val CHANGE_PASSWORD_URL = "/auth/changePassword"
        const val LOGOUT_URL = "/auth/logout"
        const val LOGOUT_SUCCESS_URL = "/auth/logoutSuccess"
        const val WEBJARS_URL = "/webjars/**"
        const val STATIC_RESOURCES_URL = "/static/**"
    }
}