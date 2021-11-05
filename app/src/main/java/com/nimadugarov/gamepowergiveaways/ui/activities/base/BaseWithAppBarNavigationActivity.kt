package com.nimadugarov.gamepowergiveaways.ui.activities.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nimadugarov.gamepowergiveaways.R
import com.sequenia.app_bar_provider.AppBarProvider
import com.sequenia.app_bar_provider.AppBarProviderImp
import com.sequenia.app_bar_provider.AppBarViews

/**
 * Базовая Activity c AppBar и навигацией
 */
abstract class BaseWithAppBarNavigationActivity(
    @LayoutRes contentLayoutId: Int
) : BaseNavigationActivity(contentLayoutId), AppBarProvider,
    AppBarViews {

    private lateinit var appBarProvider: AppBarProviderImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAppBar()
    }

    override fun getAppBarProviderImp(): AppBarProviderImp = appBarProvider

    override fun getCollapsingContent(): ViewGroup = findViewById(R.id.collapsing_content)

    override fun getAppBar(): AppBarLayout = findViewById(R.id.app_bar)

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)

    override fun getCollapsingToolbarLayout(): CollapsingToolbarLayout =
        findViewById(R.id.collapsing_toolbar)

    private fun initAppBar() {
        appBarProvider = AppBarProviderImp(this)
        setSupportActionBar(toolbar)
    }
}