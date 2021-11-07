package com.nimadugarov.gamerpowergiveaways.data.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Manager для создания сервиса API и настройки сетевых запросов
 */
class NetworkManager(
    private val baseUrl: String,
    private val timeoutInMilliseconds: Long
) {

    fun createApiService(): ApiService = createRetrofit().create(ApiService::class.java)

    private fun createRetrofit() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(createGsonConverterFactory())
        .client(createOkHttpClient())
        .build()

    private fun createGsonConverterFactory() = GsonConverterFactory.create(Gson())

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(timeoutInMilliseconds, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(timeoutInMilliseconds, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(timeoutInMilliseconds, TimeUnit.MILLISECONDS)
        addInterceptors(okHttpClient)
        return okHttpClient.build()
    }

    private fun addInterceptors(okHttpClient: OkHttpClient.Builder) {
        okHttpClient.addNetworkInterceptor(getInterceptorLogging())
    }

    private fun getInterceptorLogging(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
}