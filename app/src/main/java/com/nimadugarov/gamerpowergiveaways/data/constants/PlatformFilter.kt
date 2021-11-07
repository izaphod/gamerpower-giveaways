package com.nimadugarov.gamerpowergiveaways.data.constants

/**
 * Константы с фильтрами по платформам, для которых предназначены игры или внутриигровые предметы
 */
enum class PlatformFilter(val value: String) {
    PC("pc"),
    STEAM("steam"),
    EPIC_GAMES_STORE("epic-games-store"),
    UBISOFT("ubisoft"),
    GOG("gog"),
    BATTLENET("battlenet"),
    ORIGIN("origin"),
    XBOX_ONE("xbox-one"),
    XBOX_SERIES_XS("xbox-series-xs"),
    PS4("ps4"),
    PS5("ps5"),
    SWITCH("switch"),
    ANDROID("android"),
    IOS("ios")
}