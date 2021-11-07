package com.nimadugarov.gamerpowergiveaways.ui.fragments.base

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import com.sequenia.app_bar_provider.AppBarProvider
import com.sequenia.app_bar_provider.AppBarSettings

/**
 * Базовый Fragment c AppBar и навигацией
 */
abstract class BaseWithAppBarNavigationFragment(
    @LayoutRes contentLayoutId: Int
) : BaseNavigationFragment(contentLayoutId), AppBarSettings {
    protected var appBarProvider: AppBarProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // обработка пунктов меню в toolbar
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppBarProvider) {
            appBarProvider = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appBarProvider?.setAppBarSettings(this)
    }

    override fun onDetach() {
        super.onDetach()
        appBarProvider = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}