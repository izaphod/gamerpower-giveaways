package com.nimadugarov.gamerpowergiveaways.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Entity для раздаваемой игры
 */
data class Giveaway(
    val id: Long?,
    val title: String?,
    @SerializedName("image")
    val imageUrl: String?,
    val platforms: String?,
    @SerializedName("end_date")
    val endDate: String?
)
