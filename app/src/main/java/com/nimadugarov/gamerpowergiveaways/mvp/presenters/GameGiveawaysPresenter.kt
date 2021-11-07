package com.nimadugarov.gamerpowergiveaways.mvp.presenters

import com.nimadugarov.gamerpowergiveaways.data.constants.PlatformFilter
import com.nimadugarov.gamerpowergiveaways.data.constants.SortBy
import com.nimadugarov.gamerpowergiveaways.data.constants.TypeFilter
import com.nimadugarov.gamerpowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamerpowergiveaways.mvp.models.GiveawayModel
import com.nimadugarov.gamerpowergiveaways.mvp.models.entities.GiveawaysState
import com.nimadugarov.gamerpowergiveaways.mvp.presenters.base.BasePresenter
import com.nimadugarov.gamerpowergiveaways.mvp.views.GameGiveawaysView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
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
            giveawayModel
                .subscribeGiveaways()
                .flowOn(Dispatchers.Default)
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