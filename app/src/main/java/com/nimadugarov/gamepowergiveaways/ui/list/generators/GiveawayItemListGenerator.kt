package com.nimadugarov.gamepowergiveaways.ui.list.generators

import com.nimadugarov.gamepowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamepowergiveaways.ui.data.listitem.ListItem

/**
 * Генератор списка [раздач][Giveaway] в виде списка [элементов][ListItem]
 */
class GiveawayItemListGenerator {

    fun generateGiveawayItemList(giveaways: List<Giveaway>): List<ListItem> {
        return giveaways.map { giveaway ->
            ListItem(
                id = giveaway.id.toString(),
                data = giveaway
            )
        }
    }
}