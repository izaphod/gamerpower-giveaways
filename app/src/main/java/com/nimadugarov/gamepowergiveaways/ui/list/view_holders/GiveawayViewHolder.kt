package com.nimadugarov.gamepowergiveaways.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nimadugarov.extensions.image_loader.ImageLoader
import com.nimadugarov.gamepowergiveaways.R
import com.nimadugarov.gamepowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamepowergiveaways.databinding.GameGiveawayItemBinding
import com.nimadugarov.gamepowergiveaways.ui.data.listitem.ListItem
import com.nimadugarov.gamepowergiveaways.ui.list.view_holders.base.BaseViewHolder

/**
 * ViewHolder для раздачи
 */
class GiveawayViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.game_giveaway_item) {

    private val binding = GameGiveawayItemBinding.bind(itemView)
    private lateinit var giveaway: Giveaway
    private lateinit var listener: GameGiveawayViewHolderListener

    fun bind(listItem: ListItem, listener: GameGiveawayViewHolderListener) {
        this.giveaway = listItem.data as Giveaway
        this.listener = listener

        showGiveaway()
        setListener()
    }

    private fun showGiveaway() {
        binding.gameTitleText.text = giveaway.title
        ImageLoader
            .load(giveaway.imageUrl)
            .centerCrop()
            .into(binding.gameImage)
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onGiveawayClick(giveaway.id)
        }
    }

    /**
     * Listener для отслеживания клика по раздаче
     */
    interface GameGiveawayViewHolderListener {
        /**
         * Callback для обработки клика по [раздаче][Giveaway],
         * где [giveawayId] - id данной раздачи
         */
        fun onGiveawayClick(giveawayId: Long?)
    }
}