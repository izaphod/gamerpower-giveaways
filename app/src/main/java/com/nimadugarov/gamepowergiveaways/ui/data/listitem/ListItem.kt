package com.nimadugarov.gamepowergiveaways.ui.data.listitem

/**
 * Класс данных для RecyclerView
 */
data class ListItem(
    var type: ListItemTypes? = null,
    var id: String? = null,
    var data: Any,
    var settings: Settings? = null
)
