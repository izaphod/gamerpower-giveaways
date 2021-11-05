package com.nimadugarov.gamepowergiveaways.mvp.presenters.base

import com.nimadugarov.gamepowergiveaways.mvp.views.base.BaseView
import moxy.MvpPresenter

/**
 * Базовый презентер
 */
open class BasePresenter<View : BaseView> : MvpPresenter<View>()