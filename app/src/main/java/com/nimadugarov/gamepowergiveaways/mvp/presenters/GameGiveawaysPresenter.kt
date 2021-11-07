package com.nimadugarov.gamepowergiveaways.mvp.presenters

import com.nimadugarov.gamepowergiveaways.data.constants.PlatformFilter
import com.nimadugarov.gamepowergiveaways.data.constants.SortBy
import com.nimadugarov.gamepowergiveaways.data.constants.TypeFilter
import com.nimadugarov.gamepowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamepowergiveaways.mvp.models.GiveawayModel
import com.nimadugarov.gamepowergiveaways.mvp.models.entities.GiveawaysState
import com.nimadugarov.gamepowergiveaways.mvp.presenters.base.BasePresenter
import com.nimadugarov.gamepowergiveaways.mvp.views.GameGiveawaysView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import moxy.presenterScope

/**
 * Presenter для экрана со списком раздаваемых игр
 */
class GameGiveawaysPresenter(
    private val giveawayModel: GiveawayModel
) : BasePresenter<GameGiveawaysView>() {

    private var giveawayList: List<Giveaway>? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadGameGiveaways()
        subscribeGiveaways()
    }

    fun onGameGiveawayClicked(giveawayId: Long?) {
        giveawayId?.let {
            viewState.showGameGiveawayDetails(giveawayId)
        }
    }

    fun reloadGameGiveaways() {
        loadGameGiveaways()
    }

    private fun loadGameGiveaways() {
        giveawayList = null
        giveawayModel.resetGiveaways(
            platform = PlatformFilter.PC.value,
            type = TypeFilter.GAME.value,
            sortBy = SortBy.POPULARITY.value
        )
    }

    private fun subscribeGiveaways() {
        presenterScope.launch {
            giveawayModel.subscribeGiveaways()
                .collect { giveawaysState -> showGiveawaysState(giveawaysState) }
        }
    }

    private fun showGiveawaysState(giveawaysState: GiveawaysState) {
        giveawayList = giveawaysState.data
        val checker = GiveawaysStateChecker(giveawaysState, giveawayList)
        val error = giveawaysState.error
        when {
            checker.isLoading -> {
                viewState.startContentLoading()
            }
            checker.isLoadedSuccessful -> {
                viewState.endContentLoading()
                viewState.showGameGiveaways(giveawayList!!)
            }
            checker.isLoadedWithError -> {
                viewState.endContentLoading()
                viewState.showContentLoadingError(error!!)
            }
        }
    }
}

/**
 * Класс для проверки [состояния списка раздач][GiveawaysState]
 */
class GiveawaysStateChecker(
    private val giveawaysState: GiveawaysState,
    private val giveawayList: List<Giveaway>?
) {
    val isLoading get() = giveawaysState.isLoading && giveawayList == null
    val isLoadedSuccessful get() = giveawaysState.isLoading.not() && giveawayList != null
    val isLoadedWithError get() =
        giveawaysState.isLoading.not() && giveawaysState.error != null && giveawayList == null
}