package com.nimadugarov.gamepowergiveaways.data.network.entities

import com.nimadugarov.gamepowergiveaways.data.entities.Giveaway

/**
 * Entity для списка [раздаваемых игр][Giveaway]
 */
data class GiveawayResponse(
    val giveaways: List<Giveaway>
)
