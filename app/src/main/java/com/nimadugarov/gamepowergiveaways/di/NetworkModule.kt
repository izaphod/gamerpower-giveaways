package com.nimadugarov.gamepowergiveaways.di

import com.nimadugarov.gamepowergiveaways.data.network.NetworkManager
import com.nimadugarov.gamepowergiveaways.mvp.models.gateways.GiveawayGateway
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