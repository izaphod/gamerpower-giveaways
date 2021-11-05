package com.nimadugarov.gamepowergiveaways.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Entity для деталей раздаваемой игры
 */
data class GameGiveawayDetails(
    val id: Long?,
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnailUrl: String?,
    @SerializedName("image")
    val imageUrl: String?,
    val description: String?,
    val instructions: String?,
    @SerializedName("open_giveaway_url")
    val openGiveawayUrl: String?,
    val type: String?,
    val platforms: String?,
    @SerializedName("end_date")
    val endDate: String?,
    val status: String?,
    @SerializedName("gamerpower_url")
    val gamerPowerUrl: String?,
)
