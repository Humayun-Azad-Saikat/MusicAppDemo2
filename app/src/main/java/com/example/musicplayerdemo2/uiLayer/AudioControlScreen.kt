package com.example.musicplayerdemo2.uiLayer

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musicplayerdemo2.R
import com.example.musicplayerdemo2.viewModel.AudioControlScreenViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import com.example.musicplayerdemo2.model.DataStorePrefUtils
import com.example.musicplayerdemo2.viewModel.AudioListScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


//@Preview(showBackground = true)
@Composable
fun PlayerScreen(viewModel: AudioControlScreenViewModel = hiltViewModel()){

    val dataStorePrefUtils = viewModel.dataStorePrefUtils

    var seekPosition: Float by remember { mutableStateOf(0f) }
    var duration: Long by remember { mutableStateOf(1L) }

    LaunchedEffect(key1 =  true) {
        while (true){
            seekPosition = viewModel.getCurrentSeekPosition().toFloat()
            duration = viewModel.getDuration()
            delay(300)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${viewModel.getAudioName()}", fontSize = 24.sp,fontFamily = FontFamily.Serif, modifier = Modifier.basicMarquee(), color = Color.Blue)

            Image(
                modifier = Modifier.size(500.dp),
                bitmap = if(viewModel.getAlbum()?.asImageBitmap() != null){
                    viewModel.getAlbum()!!.asImageBitmap()
                } else{
                    ImageBitmap.imageResource(R.drawable.audioalbum)
                },
                contentDescription = ""
            )

            Row() {
                Text("${viewModel.formatTime(seekPosition.toLong())}", modifier = Modifier.weight(1f), fontSize = 20.sp, fontFamily = FontFamily.Serif, color = Color.Blue)
                Text("${viewModel.formatTime(duration)}", modifier = Modifier, fontSize = 20.sp,fontFamily = FontFamily.Serif, color = Color.Blue)
            }

            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = seekPosition,
                valueRange = 0f..duration.toFloat(),
                onValueChange = {seekPosition = it},
                onValueChangeFinished = {viewModel.seekTo(seekPosition.toLong())},
                colors = SliderDefaults.colors(
                    thumbColor = Color.Blue,
                    activeTrackColor = Color.Blue,
                    inactiveTrackColor = Color.LightGray,
                )
            )



            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(R.drawable.previousicon),
                    contentDescription = "play",
                    Modifier
                        .size(80.dp)
                        .clickable(onClick = {
                            viewModel.previousTrack()
                            saveAudioName_Uri(dataStorePrefUtils,viewModel)
                        }),

                    tint = Color.Blue
                )

                Icon(
                    painter = if (viewModel.media3Components.isPlaying()) painterResource(R.drawable.pauseicon) else painterResource(R.drawable.playicon),
                   // imageVector = Icons.Filled.,
                    contentDescription = "playPause",
                    Modifier
                        .size(80.dp)
                        .clickable(onClick = {
                            Log.d("audioPlay", "AudioControlOnFooter: ${viewModel.media3Components.isPlaying()}")
                            //if (!viewModel.media3Components.isPlaying()) {
                            if (!viewModel.media3Components.isPlaying()) {
                                viewModel.playAudio()

                                //Log.d("audioplay", "${viewModel.media3Components.isPlaying()}")
                            } else {
                                viewModel.pauseAudio()
                            }
                        }),

                    tint = Color.Blue
                )

                Icon(
                    painter = painterResource(R.drawable.nexticon),
                    contentDescription = "play",
                    Modifier
                        .size(80.dp)
                        .clickable(onClick = {
                            viewModel.nextTrack()
                            saveAudioName_Uri(dataStorePrefUtils,viewModel)
                        }),

                    tint = Color.Blue
                )

            }

        }
    }


}

fun saveAudioName_Uri(dataStorePrefUtils: DataStorePrefUtils,viewModel: AudioControlScreenViewModel){


    GlobalScope.launch(Dispatchers.IO){
        dataStorePrefUtils.saveTrackName(viewModel.getAudioName())
        dataStorePrefUtils.saveAudioUri(viewModel.getCurrentPlayingAudioUri().toString())
    }

//    scope.launch {
//
//    }
}