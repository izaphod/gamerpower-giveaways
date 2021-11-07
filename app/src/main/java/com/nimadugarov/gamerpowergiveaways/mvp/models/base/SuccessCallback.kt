package com.nimadugarov.gamerpowergiveaways.mvp.models.base

/**
 * Callback на случай успешного завершения загрузки
 */
interface SuccessCallback<Type> {

    fun onSuccess(data: Type)
}