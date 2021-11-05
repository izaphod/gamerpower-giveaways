package com.nimadugarov.gamepowergiveaways.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.nimadugarov.gamepowergiveaways.R
import com.nimadugarov.gamepowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamepowergiveaways.databinding.GameGiveawaysFragmentBinding
import com.nimadugarov.gamepowergiveaways.mvp.presenters.GameGiveawaysPresenter
import com.nimadugarov.gamepowergiveaways.mvp.views.GameGiveawaysView
import com.nimadugarov.gamepowergiveaways.ui.extensions.ContentLoading
import com.nimadugarov.gamepowergiveaways.ui.extensions.ListExtension
import com.nimadugarov.gamepowergiveaways.ui.extensions.snackbar_holder.DefaultSnackBarHolder
import com.nimadugarov.gamepowergiveaways.ui.extensions.snackbar_holder.SnackBarActionListener
import com.nimadugarov.gamepowergiveaways.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.nimadugarov.gamepowergiveaways.ui.list.adapters.GiveawayAdapter
import com.nimadugarov.gamepowergiveaways.ui.list.generators.GiveawayItemListGenerator
import com.nimadugarov.gamepowergiveaways.ui.list.view_holders.GiveawayViewHolder.GameGiveawayViewHolderListener
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get

/**
 * Fragment для отображения списка раздаваемых игр
 */
class GameGiveawaysFragment : BaseWithAppBarNavigationFragment(R.layout.game_giveaways_fragment),
    ContentLoading, GameGiveawaysView, GameGiveawayViewHolderListener, SnackBarActionListener {

    private var binding: GameGiveawaysFragmentBinding? = null
    private var adapter: GiveawayAdapter? = null
    private var toolbarTitleView: TextView? = null

    private val itemListGenerator = GiveawayItemListGenerator()

    private val presenter by moxyPresenter {
        get<GameGiveawaysPresenter>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GameGiveawaysFragmentBinding.bind(view)
        initViews()
        setToolbarTitle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        adapter = null
    }

    override fun showGameGiveaways(gameGiveaways: List<Giveaway>) {
        val gameGiveawayItemList = itemListGenerator.generateGiveawayItemList(gameGiveaways)
        adapter?.updateItems(gameGiveawayItemList)
    }

    override fun showGameGiveawayDetails(giveawayId: Long) {
        // todo навигаци на экран с детальной информацией
    }

    override fun showContentLoadingError(error: String) {
        DefaultSnackBarHolder(viewLifecycleOwner, binding!!.root)
            .showIndefiniteDurationMessage(
                message = error,
                actionText = getString(R.string.button_retry),
                actionListener = this
            )
    }

    override fun getContentView(): View? = binding?.giveawayList

    override fun getContentLoadingView(): View? = binding?.progressView?.loadingView

    override fun onGiveawayClick(giveawayId: Long?) {
        presenter.onGameGiveawayClicked(giveawayId)
    }

    override fun onActionClick() {
        presenter.reloadGameGiveaways()
    }

    private fun initViews() {
        initList()
        toolbarTitleView =
            appBarProvider?.setCustomToolbarView(R.layout.toolbar_title_view) as TextView
    }

    private fun initList() {
        val listExtension = ListExtension(binding?.giveawayList)
        adapter = GiveawayAdapter(
            layoutInflater = layoutInflater,
            gameGiveawayViewHolderListener = this
        )
        listExtension.setAdapter(adapter!!)
    }

    private fun setToolbarTitle() {
        toolbarTitleView?.text = getString(R.string.title_toolbar_game_giveaways)
    }
}