package com.example.musicplayerdemo2

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.musicplayerdemo2.model.Media3Components
import com.example.musicplayerdemo2.navigation.NavigationHost
import com.example.musicplayerdemo2.navigation.ScreenDestination
import com.example.musicplayerdemo2.ui.theme.MusicPlayerDemo2Theme
import com.example.musicplayerdemo2.uiLayer.AudioListScreen
import com.example.musicplayerdemo2.uiLayer.PlayerScreen
import com.example.musicplayerdemo2.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


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
