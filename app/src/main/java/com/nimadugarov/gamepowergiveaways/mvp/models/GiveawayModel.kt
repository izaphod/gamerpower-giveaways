package com.nimadugarov.gamepowergiveaways.mvp.models

import com.nimadugarov.gamepowergiveaways.data.entities.GiveawayDetails
import com.nimadugarov.gamepowergiveaways.mvp.models.base.BaseCallback
import com.nimadugarov.gamepowergiveaways.mvp.models.entities.GiveawaysState
import kotlinx.coroutines.flow.Flow

/**
 * Модель для методов загрузки раздач и детальной информации о них
 */
interface GiveawayModel {

    /**
     * Обновить [список раздач][GiveawaysState.data], где:
     *
     * [platform] платформа (pc, xbox-series-xs, ps5, android и другие)
     *
     * [type]     тип раздачи (игры, бета версии игр, внутриигровые предметы)
     *
     * [sortBy]   тип сортировки (по дате, названию или популярности)
     */
    fun resetGiveaways(
        platform: String,
        type: String,
        sortBy: String
    )

    /**
     * Подписаться на [изменение списка раздач][GiveawaysState]
     */
    fun subscribeGiveaways(): Flow<GiveawaysState>

    /**
     * Загрузить [подробную информацию о раздаче][GiveawayDetails] по ее [id][giveawayId]
     *
     * [callback] callback для загрузки списка жанров
     */
    fun getGiveawayDetails(
        giveawayId: Long,
        callback: GetGiveawayDetailsCallback
    )

    /**
     * Callback для загрузки списка фильмов
     */
    interface GetGiveawayDetailsCallback : BaseCallback<GiveawayDetails>
}