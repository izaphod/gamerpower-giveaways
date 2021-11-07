package com.nimadugarov.gamerpowergiveaways.mvp.models.gateways

import com.nimadugarov.gamerpowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamerpowergiveaways.data.entities.GiveawayDetails
import com.nimadugarov.gamerpowergiveaways.data.network.ApiService
import retrofit2.Call

/**
 * Класс с методами зазрузки раздач и детальной информации о них
 */
class GiveawayGateway(private val apiService: ApiService) {

    fun getGiveawaysCall(
        platform: String,
        type: String,
        sortBy: String
    ): Call<List<Giveaway>> = apiService.getGiveaways(
        platform = platform,
        type = type,
        sortBy = sortBy
    )

    fun getGiveawayDetailsCall(giveawayId: Long): Call<GiveawayDetails> =
        apiService.getGiveawayDetails(giveawayId)
}