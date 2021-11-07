package com.nimadugarov.gamerpowergiveaways.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.nimadugarov.gamerpowergiveaways.R
import com.nimadugarov.gamerpowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamerpowergiveaways.databinding.GameGiveawaysFragmentBinding
import com.nimadugarov.gamerpowergiveaways.mvp.presenters.GameGiveawaysPresenter
import com.nimadugarov.gamerpowergiveaways.mvp.views.GameGiveawaysView
import com.nimadugarov.gamerpowergiveaways.ui.extensions.ContentLoading
import com.nimadugarov.gamerpowergiveaways.ui.extensions.ListExtension
import com.nimadugarov.gamerpowergiveaways.ui.extensions.snackbar_holder.DefaultSnackBarHolder
import com.nimadugarov.gamerpowergiveaways.ui.extensions.snackbar_holder.SnackBarActionListener
import com.nimadugarov.gamerpowergiveaways.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.nimadugarov.gamerpowergiveaways.ui.list.adapters.GiveawayAdapter
import com.nimadugarov.gamerpowergiveaways.ui.list.generators.GiveawayItemListGenerator
import com.nimadugarov.gamerpowergiveaways.ui.list.view_holders.GiveawayViewHolder.GameGiveawayViewHolderListener
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.game_giveaways_filter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.game_giveaways_filter -> onFilterMenuClick()
        }
        return super.onOptionsItemSelected(item)
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
        // todo навигация на экран с детальной информацией
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

    private fun onFilterMenuClick() {
        // todo открыть диалог с фильтрами
    }
}