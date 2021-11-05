package com.nimadugarov.gamepowergiveaways.mvp.models.entities

import com.nimadugarov.gamepowergiveaways.data.entities.Giveaway

/**
 * Entity для состояния списка [раздач][Giveaway]
 */
data class GiveawaysState(
    val data: List<Giveaway>? = null,
    val error: String? = null,
    var isLoading: Boolean = false,
)
