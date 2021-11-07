package com.nimadugarov.gamerpowergiveaways.data.network.base

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.Headers

/**
 * Базовый callback для Retrofit
 */
interface NetworkCallback<Type> : Callback<Type> {

    override fun onResponse(call: Call<Type>, response: Response<Type>) {
        if (response.isSuccessful) {
            onSuccess(response.body(), response.headers())
        } else {
            handleError(response)
        }
    }

    override fun onFailure(call: Call<Type>, throwable: Throwable) {
        throwable.printStackTrace()
        if (call.isCanceled) {
            return
        }
        val errorMessage = NetworkErrors.translateThrowable(throwable)
        onError(errorMessage, null)
    }

    fun handleError(response: Response<Type>?) {
        response?.let {
            onDefaultError(it)
        }
    }

    /**
     * Срабатывает при успехе
     *
     * @param response ответ от сервера
     */
    fun onSuccess(response: Type?) {
    }

    /**
     * Срабатывает при успехе
     *
     * @param response ответ от сервера
     * @param headers  хедеры
     */
    fun onSuccess(response: Type?, headers: Headers) {
        onSuccess(response)
    }

    /**
     * Срабатывает при ошибке запроса
     *
     * @param error текст ошибки
     */
    fun onError(error: String) {
    }

    /**
     * Срабатывает при ошибке запроса
     *
     * @param error        текст ошибки
     * @param responseCode код ответа от сервера
     */
    fun onError(error: String, responseCode: Int?) {
        onError(error)
    }

    /**
     * Показ ошибки с текстом по умолчанию
     */
    private fun onDefaultError(response: Response<Type>) {
        val errorMessage = NetworkErrors.defaultError()
        onError(errorMessage, response.code())
    }
}