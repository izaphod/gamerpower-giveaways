package com.nimadugarov.gamerpowergiveaways.di

import com.nimadugarov.gamerpowergiveaways.data.network.NetworkManager
import com.nimadugarov.gamerpowergiveaways.mvp.models.gateways.GiveawayGateway
import org.koin.dsl.module

/**
 * Файл koin модуля с определением сервисов API
 */
val networkModule = module {
    val timeoutInMilliseconds: Long = 60000
    val apiUrl = "https://www.gamerpower.com/api/"

    single { NetworkManager(apiUrl, timeoutInMilliseconds).createApiService() }
    single { GiveawayGateway(apiService = get()) }
}