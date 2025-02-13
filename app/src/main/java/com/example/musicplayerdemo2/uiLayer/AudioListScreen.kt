package com.example.musicplayerdemo2.uiLayer

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.musicplayerdemo2.navigation.ScreenDestination
import com.example.musicplayerdemo2.viewModel.AudioListScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
//fun AudioListScreen(viewModel: MusicViewModel = hiltViewModel(),media3Components: Media3Components,navHostController: NavHostController){
fun AudioListScreen(viewModel: AudioListScreenViewModel = hiltViewModel(), navHostController: NavHostController){

    //val navHostController = rememberNavController()

    val audioFiles = viewModel.audioFiles.observeAsState(initial = emptyList())


    LaunchedEffect(key1 = true) {
        viewModel.getMusic()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF335bff)),
        contentPadding = WindowInsets.systemBars.asPaddingValues(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(audioFiles.value){

            val isPlaying = viewModel.media3Components.isPlaying()
            val currentUri = it.uri.toString()
            val currentPlayingAudioUri = viewModel.getCurrentPlayingAudioUri()


            val playingNameTextColor: Color = if(isPlaying && currentUri == currentPlayingAudioUri) Color(0xFFff3333) else Color.Blue
            val playingCardColor: Color = if(isPlaying && currentUri == currentPlayingAudioUri) Color(0xFFb5ff20) else Color.Cyan



            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .height(90.dp)
                    .padding(0.dp, 6.dp, 0.dp, 0.dp)

                    .combinedClickable(

                        onClick = {
                            handleAudioClick(viewModel,currentUri,isPlaying, currentPlayingAudioUri,navHostController)
                        },

//                        onLongClick = {
//                            viewModel.deleteAudio(it.uri)
//                        }

                    ),

                // artist color 0xFFff8a33
                colors = CardDefaults.cardColors(containerColor = playingCardColor),
            ) {
                Text("${nameString(it.name)}", color = playingNameTextColor, textAlign = TextAlign.Center, fontSize = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, fontFamily = FontFamily.Serif)
                Spacer(modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp))
                Text("Artist:${it.artist}", color = Color(color =0xFFff8a33), textAlign = TextAlign.Center,maxLines = 1, overflow = TextOverflow.Ellipsis, fontFamily = FontFamily.Serif)
            }
        }
    }

}

fun nameString(name: String): String{
    return name.replace("_"," ").replace("-"," ")
}


private fun handleAudioClick(
    viewModel: AudioListScreenViewModel,
    currentUri: String,
    isPlaying: Boolean,
    currentPlayingUri: String?,
    navHostController: NavHostController
) {
    if (!isPlaying || currentUri != currentPlayingUri) {
        viewModel.loadPlaylist(
            viewModel.audioFiles.value?.map { it.uri.toString() } ?: emptyList(),
            currentUri
        )
        viewModel.getCurrentAudio(currentUri)
        viewModel.playAudio()
    }
    navHostController.navigate(ScreenDestination.PlayerScreen)
}