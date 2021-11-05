package com.nimadugarov.gamepowergiveaways.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Entity для раздаваемой игры
 */
data class GameGiveaway(
    val id: Long?,
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnailUrl: String?,
    val platforms: String?,
    @SerializedName("end_date")
    val endDate: String?
)
