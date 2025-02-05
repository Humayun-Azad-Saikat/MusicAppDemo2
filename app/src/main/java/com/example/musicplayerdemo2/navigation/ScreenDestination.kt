package com.example.musicplayerdemo2.navigation

import kotlinx.serialization.Serializable

sealed class ScreenDestination() {

    @Serializable
    object PlayerScreen: ScreenDestination()

    @Serializable
    object AudioListScreen: ScreenDestination()

}