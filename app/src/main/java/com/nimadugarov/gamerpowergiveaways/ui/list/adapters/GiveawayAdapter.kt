package com.nimadugarov.gamerpowergiveaways.ui.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nimadugarov.gamerpowergiveaways.ui.data.listitem.ListItem
import com.nimadugarov.gamerpowergiveaways.ui.list.adapters.base.BaseAdapter
import com.nimadugarov.gamerpowergiveaways.ui.list.view_holders.GiveawayViewHolder
import com.nimadugarov.gamerpowergiveaways.ui.list.view_holders.GiveawayViewHolder.GameGiveawayViewHolderListener
import com.nimadugarov.gamerpowergiveaways.ui.list.view_holders.base.BaseViewHolder

/**
 * Адаптер для списка раздач
 */
class GiveawayAdapter(
    layoutInflater: LayoutInflater,
    private val gameGiveawayViewHolderListener: GameGiveawayViewHolderListener
) : BaseAdapter<ListItem, BaseViewHolder>(layoutInflater) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        GiveawayViewHolder(layoutInflater, parent)

    override fun onBindViewHolder(holder: BaseViewHolder, item: ListItem) {
        (holder as GiveawayViewHolder).bind(item, gameGiveawayViewHolderListener)
    }
}