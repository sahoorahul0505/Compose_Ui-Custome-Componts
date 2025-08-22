package com.kodebug.customecomponets.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object Home : Routes()
    @Serializable
    object HomeCardsSourceCode : Routes()


    @Serializable
    object ShadowButton : Routes()
    @Serializable
    object ShadowButtonSourceCode : Routes()


    @Serializable
    object ExpandableCard : Routes()
    @Serializable
    object ExpandableCardSourceCode : Routes()
}