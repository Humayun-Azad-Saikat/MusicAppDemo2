package com.example.musicplayerdemo2.uiLayer

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.musicplayerdemo2.navigation.ScreenDestination
import com.example.musicplayerdemo2.viewModel.AudioListScreenViewModel
import com.example.musicplayerdemo2.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.musicplayerdemo2.model.DataStorePrefUtils
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.StrokeCap
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
//fun AudioListScreen(viewModel: MusicViewModel = hiltViewModel(),media3Components: Media3Components,navHostController: NavHostController){
fun AudioListScreen(viewModel: AudioListScreenViewModel = hiltViewModel(), navHostController: NavHostController){

    //val navHostController = rememberNavController()

    val audioFiles = viewModel.audioFiles.observeAsState(initial = emptyList())
 //   val isPlaying = viewModel.media3Components.isPlaying()
    //val isPlaying = viewModel.isPlaying()
    val isPlaying by viewModel.isPlaying


    val dataStorePrefUtils = viewModel.dataStorePrefUtils

    Log.d("dataStorePrefUtils", "AudioListScreen: $dataStorePrefUtils")



    LaunchedEffect(key1 = true) {
        viewModel.getMusic()
    }

    LaunchedEffect(isPlaying) {
        viewModel.isPlaying()
        viewModel.getAudioName()
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color(0xFF335bff)),
            contentPadding = WindowInsets.systemBars.asPaddingValues(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(audioFiles.value){

//                val isPlaying = viewModel.media3Components.isPlaying()
                val currentUri = it.uri.toString()
                val currentPlayingAudioUri = viewModel.getCurrentPlayingAudioUri()


            val playingNameTextColor: Color = if(isPlaying && currentUri == currentPlayingAudioUri) Color(0xFFff3333) else Color.Blue
            val playingCardColor: Color = if(isPlaying && currentUri == currentPlayingAudioUri) Color(0xFFb5ff20) else Color.Cyan

                val scope = rememberCoroutineScope()


                //loadPlaylist(viewModel,currentUri)

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(90.dp)
                        .padding(0.dp, 6.dp, 0.dp, 0.dp)

                        .combinedClickable(

                            onClick = {
                                handleAudioClick(
                                    viewModel,
                                    currentUri,
                                    isPlaying,
                                    currentPlayingAudioUri,
                                    navHostController
                                )

                                //if (isPlaying) {
                                //  loadPlaylist(viewModel,it.uri.toString())
                                // }

                                scope.launch {
                                    dataStorePrefUtils.saveTrackName(nameString(it.name))
                                    dataStorePrefUtils.saveAudioUri(it.uri.toString())
                                }


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

        ProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            viewModel,
            isPlaying
        )
        AudioControlOnFooter(
            viewModel,
            isPlaying,
            dataStorePrefUtils,
            navHostController
        )

    }

}


@Composable
private fun ProgressIndicator(modifier: Modifier,viewModel: AudioListScreenViewModel,isPlaying: Boolean){
    var seekPosition: Float by remember { mutableStateOf(0f) }
    var duration: Long by remember { mutableStateOf(1L) }


    val progress = if (duration > 0 ){
        (seekPosition.toFloat()/duration.toFloat()).coerceIn(0f,1f)
    }else{
        0f
    }

    LaunchedEffect(isPlaying) {
        while (isPlaying){
            seekPosition = viewModel.getCurrentSeekPosition().toFloat()
            duration = viewModel.getDuration().coerceAtLeast(1L)
            delay(300)
        }
    }


    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier.height(5.dp),
        color = Color.Red,
        trackColor = Color.LightGray

    )

}


@Composable
private fun AudioControlOnFooter(viewModel: AudioListScreenViewModel = hiltViewModel(),isPlaying: Boolean,dataStorePrefUtils: DataStorePrefUtils,navHostController: NavHostController){


    var loadedAudioName by remember { mutableStateOf("") }
    var loadedAudioUri by remember { mutableStateOf("") }
    var playlistIsLoaded by rememberSaveable { mutableStateOf(false) }


    LaunchedEffect(key1 = Unit) {
        loadedAudioName = dataStorePrefUtils.getTrackName().toString()
        loadedAudioUri = dataStorePrefUtils.getAudioUri().toString()

        if (!playlistIsLoaded){
            loadPlaylist(viewModel,loadedAudioUri)
            playlistIsLoaded = true
            Log.d("triggering", "AudioControlOnFooter: i am ${isPlaying}")
        }

        Log.d("loadedAudioName", "AudioControlOnFooter: $loadedAudioUri")
    }

//    val loadedAudioName by dataStorePrefUtils.getTrackName.collectAsState(initial = "Unknown Track")
//    val loadedAudioUri by dataStorePrefUtils.getAudioUri.collectAsState(initial = "")



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .background(color = Color.Yellow)
            .clickable(
                onClick = {
                    navHostController.navigate(ScreenDestination.PlayerScreen)
                }

            ),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Yellow),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                loadedAudioName,
                modifier = Modifier
                    .weight(1f)
                    .basicMarquee(), fontSize = 20.sp,fontFamily = FontFamily.Serif
            )


            Icon(
                painter = if (isPlaying) painterResource(R.drawable.pauseicon) else painterResource(R.drawable.playicon),
                // imageVector = Icons.Filled.,
                contentDescription = "playPause",
                Modifier
                    .size(80.dp)
                    .clickable(onClick = {
                        if (!isPlaying) {
                            viewModel.playAudio()
                        } else {
                            viewModel.pauseAudio()
                        }
                    }),

                tint = Color.Blue
            )


        }
    }

}


private fun nameString(name: String): String{
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

private fun loadPlaylist(viewModel: AudioListScreenViewModel,currentUri: String){
    viewModel.loadPlaylist(
        viewModel.audioFiles.value?.map { it.uri.toString() } ?: emptyList(),
        currentUri
    )
}