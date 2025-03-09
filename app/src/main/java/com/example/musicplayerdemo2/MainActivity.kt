package com.example.musicplayerdemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.musicplayerdemo2.navigation.NavigationHost
import com.example.musicplayerdemo2.ui.theme.MusicPlayerDemo2Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicPlayerDemo2Theme {

                val navController = rememberNavController()
                NavigationHost(navController)
            }
        }
    }
}
