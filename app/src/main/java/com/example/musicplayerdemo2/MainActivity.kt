package com.example.musicplayerdemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> //if removed no problem

                    val navController = rememberNavController()
                    NavigationHost(navController)

                }
            }
        }
    }
}
