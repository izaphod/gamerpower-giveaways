package com.nimadugarov.gamerpowergiveaways.ui.activities.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nimadugarov.gamerpowergiveaways.R

/**
 * Базовое Activity с навигацией
 */
abstract class BaseNavigationActivity(
    @LayoutRes contentLayoutId: Int
) : BaseActivity(contentLayoutId) {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationController()
    }

    protected open fun getNavigationViewId(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.navigation_view)
    }

    protected fun setGraph(graphLayoutId: Int) {
        navigationController.setGraph(graphLayoutId)
    }

    protected fun setGraph(graphLayoutId: Int, startDestinationId: Int) {
        setGraph(graphLayoutId, startDestinationId, null)
    }

    protected fun setGraph(graphLayoutId: Int, startDestinationId: Int, data: Bundle?) {
        val graph = navigationController.navInflater.inflate(graphLayoutId)
        graph.startDestination = startDestinationId
        navigationController.setGraph(graph, data)
    }

    private fun initNavigationController() {
        navigationController = (getNavigationViewId() as NavHostFragment).navController
    }
}