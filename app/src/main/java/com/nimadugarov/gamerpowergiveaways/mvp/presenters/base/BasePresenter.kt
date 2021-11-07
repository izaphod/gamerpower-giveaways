package com.nimadugarov.gamerpowergiveaways.mvp.presenters.base

import com.nimadugarov.gamerpowergiveaways.mvp.views.base.BaseView
import moxy.MvpPresenter

/**
 * Базовый презентер
 */
open class BasePresenter<View : BaseView> : MvpPresenter<View>()