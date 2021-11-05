package com.nimadugarov.gamepowergiveaways.mvp.views

import com.sequenia.app.movieapp.mvp.views.base.ContentLoadingView

/**
 * View для экрана со списком раздаваемых игр
 */
interface GameGiveawayView : ContentLoadingView {

    /**
     * Отобразить список раздаваемых игр
     */
    fun showGameGiveaways()

    /**
     * Перейти к экрану с детальной информацией о раздаваемой игре
     */
    fun showGameGiveawayDetails()
}