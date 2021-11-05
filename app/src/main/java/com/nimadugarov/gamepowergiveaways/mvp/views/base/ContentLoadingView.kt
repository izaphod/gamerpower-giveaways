package com.sequenia.app.movieapp.mvp.views.base

import com.nimadugarov.gamepowergiveaways.mvp.views.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * Интерфейс view для экранов с загрузкой данных
 */
interface ContentLoadingView : BaseView {

    /**
     * Начать отображение на View состояния загрузки контента
     */
    @StateStrategyType(value = SingleStateStrategy::class, tag = "contentLoading")
    fun startContentLoading()

    /**
     * Завершить отображение на View состояния загрузки контента,
     * отобразить загруженный контент
     */
    @StateStrategyType(value = AddToEndSingleTagStrategy::class, tag = "contentLoading")
    fun endContentLoading()

    /**
     * Отобразить ошибку загрузки контента
     */
    @AddToEndSingle
    fun showContentLoadingError(error: String)
}