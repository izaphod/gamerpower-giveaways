package com.nimadugarov.gamerpowergiveaways.mvp.models

import com.nimadugarov.gamerpowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamerpowergiveaways.data.entities.GiveawayDetails
import com.nimadugarov.gamerpowergiveaways.data.network.base.NetworkCallback
import com.nimadugarov.gamerpowergiveaways.mvp.models.entities.GiveawaysState
import com.nimadugarov.gamerpowergiveaways.mvp.models.gateways.GiveawayGateway
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call

/**
 * Реализация [модели для методов загрузки раздач и детальной информации о них][GiveawayModel]
 */
class GiveawayModelProd(private val giveawayGateway: GiveawayGateway) : GiveawayModel {

    private var giveawaysCall: Call<List<Giveaway>>? = null
    private var giveawayDetailsCall: Call<GiveawayDetails>? = null

    private val giveawaysFlow = MutableStateFlow(GiveawaysState())

    override fun resetGiveaways(
        platform: String,
        type: String,
        sortBy: String
    ) {
        giveawaysFlow.value = GiveawaysState()
        loadGiveaways(platform, type, sortBy)
    }

    override fun subscribeGiveaways() = giveawaysFlow.asStateFlow()

    override fun getGiveawayDetails(
        giveawayId: Long,
        callback: GiveawayModel.GetGiveawayDetailsCallback
    ) {
        if (giveawayDetailsCall?.isCanceled == false) {
            giveawayDetailsCall?.cancel()
        }

        giveawayDetailsCall = giveawayGateway.getGiveawayDetailsCall(giveawayId)
        giveawayDetailsCall!!.enqueue(object : NetworkCallback<GiveawayDetails> {
            override fun onSuccess(response: GiveawayDetails?) {
                callback.onSuccess(response!!)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    private fun loadGiveaways(platform: String, type: String, sortBy: String) {
        if (giveawaysCall?.isCanceled == false) {
            giveawaysCall?.cancel()
        }

        updateGiveawaysState(
            giveaways = null,
            error = null,
            isLoading = true
        )

        giveawaysCall = giveawayGateway.getGiveawaysCall(platform, type, sortBy)
        giveawaysCall!!.enqueue(object : NetworkCallback<List<Giveaway>> {
            override fun onSuccess(response: List<Giveaway>?) {
                updateGiveawaysState(
                    giveaways = response!!,
                    error = null,
                    isLoading = false
                )
            }

            override fun onError(error: String) {
                updateGiveawaysState(
                    giveaways = null,
                    error = error,
                    isLoading = false
                )
            }
        })
    }

    private fun updateGiveawaysState(
        giveaways: List<Giveaway>?,
        error: String?,
        isLoading: Boolean
    ) {
        giveawaysFlow.value = giveawaysFlow.value.copy(
            data = giveaways,
            error = error,
            isLoading = isLoading
        )
    }
}