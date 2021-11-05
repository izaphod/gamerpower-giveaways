package com.nimadugarov.gamepowergiveaways.mvp.models.base

/**
 * Callback на случай успешного завершения загрузки
 */
interface SuccessCallback<Type> {

    fun onSuccess(data: Type)
}