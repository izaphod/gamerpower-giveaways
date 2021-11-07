package com.nimadugarov.gamerpowergiveaways.mvp.models.base

/**
 * Callback на случай ошибки
 */
interface ErrorCallback {

    fun onError(error: String)
}