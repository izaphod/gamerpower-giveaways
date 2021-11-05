package com.nimadugarov.gamepowergiveaways.di

import com.nimadugarov.gamepowergiveaways.mvp.models.GiveawayModel
import com.nimadugarov.gamepowergiveaways.mvp.models.GiveawayModelProd
import org.koin.dsl.module

/**
 * Файл koin модуля с определением моделей
 */
val giveawayModelModule = module {
    factory<GiveawayModel> {
        GiveawayModelProd(giveawayGateway = get())
    }
}