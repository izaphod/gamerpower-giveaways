package com.nimadugarov.gamerpowergiveaways.ui.list.generators

import com.nimadugarov.gamerpowergiveaways.data.entities.Giveaway
import com.nimadugarov.gamerpowergiveaways.ui.data.listitem.ListItem

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