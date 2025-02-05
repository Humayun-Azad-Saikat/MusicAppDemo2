package com.example.musicplayerdemo2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicplayerdemo2.uiLayer.AudioListScreen
import com.example.musicplayerdemo2.uiLayer.PlayerScreen


@Composable
fun NavigationHost(navHostController: NavHostController){

    NavHost(navController = navHostController, startDestination = ScreenDestination.AudioListScreen){

        composable<ScreenDestination.PlayerScreen>{
            PlayerScreen()
        }

        composable<ScreenDestination.AudioListScreen>{
            AudioListScreen(navHostController = navHostController)
        }
    }

}