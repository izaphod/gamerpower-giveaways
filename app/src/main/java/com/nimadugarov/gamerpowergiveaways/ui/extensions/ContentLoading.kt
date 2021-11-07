package com.nimadugarov.gamerpowergiveaways.ui.extensions

import android.view.View
import com.nimadugarov.gamerpowergiveaways.mvp.views.base.ContentLoadingView

/**
 * Интерфейс для управления состоянием загрузки контента на view
 */
interface ContentLoading : ContentLoadingView {

    fun getContentView(): View? = null
    fun getContentLoadingView(): View? = null

    override fun startContentLoading() {
        setContentViewVisibility(false)
        setContentLoadingViewVisibility(true)
    }

    override fun endContentLoading() {
        setContentViewVisibility(true)
        setContentLoadingViewVisibility(false)
    }

    private fun setContentViewVisibility(isVisible: Boolean) {
        getContentView()?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun setContentLoadingViewVisibility(isVisible: Boolean) {
        getContentLoadingView()?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}