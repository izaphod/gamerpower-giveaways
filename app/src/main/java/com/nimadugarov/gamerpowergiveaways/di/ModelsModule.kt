package com.nimadugarov.gamerpowergiveaways.di

import com.nimadugarov.gamerpowergiveaways.mvp.models.GiveawayModel
import com.nimadugarov.gamerpowergiveaways.mvp.models.GiveawayModelProd
import org.koin.dsl.module

/**
 * Файл koin модуля с определением моделей
 */
val giveawayModelModule = module {
    factory<GiveawayModel> {
        GiveawayModelProd(giveawayGateway = get())
    }
}