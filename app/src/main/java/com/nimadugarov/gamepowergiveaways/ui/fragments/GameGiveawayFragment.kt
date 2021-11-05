package com.nimadugarov.gamepowergiveaways.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.nimadugarov.gamepowergiveaways.R
import com.nimadugarov.gamepowergiveaways.databinding.GameGiveawaysFragmentBinding
import com.nimadugarov.gamepowergiveaways.mvp.views.GameGiveawayView
import com.nimadugarov.gamepowergiveaways.ui.extensions.ContentLoading
import com.nimadugarov.gamepowergiveaways.ui.fragments.base.BaseWithAppBarNavigationFragment

class GameGiveawayFragment : BaseWithAppBarNavigationFragment(R.layout.game_giveaways_fragment),
    ContentLoading, GameGiveawayView {

    private var binding: GameGiveawaysFragmentBinding? = null
    private var toolbarTitleView: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GameGiveawaysFragmentBinding.bind(view)
        initViews()
        setToolbarTitle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun showGameGiveaways() {
        TODO("Not yet implemented")
    }

    override fun showGameGiveawayDetails() {
        TODO("Not yet implemented")
    }

    override fun showContentLoadingError(error: String) {
        TODO("Not yet implemented")
    }

    override fun getContentView(): View? = binding?.giveawayList

    override fun getContentLoadingView(): View? = binding?.progressView?.loadingView

    private fun initViews() {
        toolbarTitleView =
            appBarProvider?.setCustomToolbarView(R.layout.toolbar_title_view) as TextView
    }

    private fun setToolbarTitle() {
        toolbarTitleView?.text = getString(R.string.title_toolbar_game_giveaways)
    }
}