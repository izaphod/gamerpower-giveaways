package com.nimadugarov.gamerpowergiveaways.mvp.models.base

/**
 * Базовый callback для модели
 */
interface BaseCallback<Type> : ErrorCallback, SuccessCallback<Type> {

    override fun onError(error: String)

    override fun onSuccess(data: Type)
}