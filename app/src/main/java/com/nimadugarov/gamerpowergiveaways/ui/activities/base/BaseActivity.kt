package com.nimadugarov.gamerpowergiveaways.ui.activities.base

import androidx.annotation.LayoutRes
import moxy.MvpAppCompatActivity

/**
 * Базовая Activity
 */
abstract class BaseActivity(
    @LayoutRes contentLayoutId: Int
) : MvpAppCompatActivity(contentLayoutId)