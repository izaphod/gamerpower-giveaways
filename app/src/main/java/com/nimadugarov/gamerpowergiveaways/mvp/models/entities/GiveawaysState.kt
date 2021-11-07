package com.nimadugarov.gamerpowergiveaways.mvp.models.entities

import com.nimadugarov.gamerpowergiveaways.data.entities.Giveaway

/**
 * Entity для состояния списка [раздач][Giveaway]
 */
data class GiveawaysState(
    val data: List<Giveaway>? = null,
    val error: String? = null,
    var isLoading: Boolean = false,
)
