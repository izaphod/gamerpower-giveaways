package com.nimadugarov.gamepowergiveaways.di

import com.nimadugarov.gamepowergiveaways.mvp.presenters.GameGiveawaysPresenter
import org.koin.dsl.module

/**
 * Файл koin модуля с определением presenter'ов для экранов
 */
val presentersModule = module {
    factory { GameGiveawaysPresenter(giveawayModel = get()) }
}