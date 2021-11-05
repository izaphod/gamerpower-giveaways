package com.nimadugarov.gamepowergiveaways.mvp.models.base

/**
 * Callback на случай ошибки
 */
interface ErrorCallback {

    fun onError(error: String)
}