package com.example.musicplayerdemo2.uiLayer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.musicplayerdemo2.model.Media3Components
import com.example.musicplayerdemo2.navigation.ScreenDestination
import com.example.musicplayerdemo2.viewModel.MusicViewModel

@Composable
//fun AudioListScreen(viewModel: MusicViewModel = hiltViewModel(),media3Components: Media3Components,navHostController: NavHostController){
fun AudioListScreen(viewModel: MusicViewModel = hiltViewModel(),navHostController: NavHostController){

    //val navHostController = rememberNavController()

    val audioFiles = viewModel.audioFiles.observeAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.getMusic()
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color(0xFF335bff)),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(audioFiles.value){
            Card(
                modifier = Modifier.fillMaxSize().height(90.dp).padding(0.dp,6.dp,0.dp,0.dp)
                    .clickable(onClick = {
                        viewModel.loadMediaUri(it.uri.toString())
                        viewModel.playAudio()
                        navHostController.navigate(ScreenDestination.PlayerScreen)
                    }),
                colors = CardDefaults.cardColors(containerColor = Color.Cyan),
            ) {
                Text("${it.name}", color = Color.Blue, textAlign = TextAlign.Center, fontSize = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp))
                Text("Artist:${it.artist}", color = Color(color = 0xFFff8a33), textAlign = TextAlign.Center)
            }
        }
    }

}