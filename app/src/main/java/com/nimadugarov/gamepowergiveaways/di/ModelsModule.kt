package com.nimadugarov.gamepowergiveaways.di

import com.nimadugarov.gamepowergiveaways.mvp.models.GiveawayModel
import com.nimadugarov.gamepowergiveaways.mvp.models.GiveawayModelProd
import org.koin.dsl.module

/**
 * Файл с определением модулей моделей
 */
val giveawayModelModule = module {
    factory<GiveawayModel> {
        GiveawayModelProd(giveawayGateway = get())
    }
}