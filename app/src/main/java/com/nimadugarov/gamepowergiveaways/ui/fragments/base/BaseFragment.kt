package com.nimadugarov.gamepowergiveaways.ui.fragments.base

import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment

/**
 * Базовый Fragment
 */
abstract class BaseFragment(
    @LayoutRes contentLayoutId: Int
) : MvpAppCompatFragment(contentLayoutId) {

    protected open fun onBackPressed() {}
}