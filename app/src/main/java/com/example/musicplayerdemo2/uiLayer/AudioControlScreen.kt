package com.example.musicplayerdemo2.uiLayer

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.runtime.setValue


//@Preview(showBackground = true)
@Composable
fun PlayerScreen(viewModel: AudioControlScreenViewModel = hiltViewModel()){


  //  val isPlaying by remember { derivedStateOf { viewModel.media3Components.isPlaying() } }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Song Name", fontSize = 24.sp)
            Image(
                modifier = Modifier.size(500.dp),
                painter = painterResource(R.drawable.audioalbum),
                contentDescription = "audioAlbum"
            )

            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = 0.5f,
                onValueChange = {}
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
                        })
                )

                Icon(
                    painter = if (viewModel.media3Components.isPlaying()) painterResource(R.drawable.playicon) else painterResource(R.drawable.pauseicon),
                   // imageVector = Icons.Filled.,
                    contentDescription = "playPause",
                    Modifier
                        .size(80.dp)
                        .clickable(onClick = {
                            //if (!viewModel.media3Components.isPlaying()) {
                            if (!viewModel.media3Components.isPlaying()) {
                                viewModel.playAudio()

                                //Log.d("audioplay", "${viewModel.media3Components.isPlaying()}")
                            } else {
                                viewModel.pauseAudio()
                            }
                        })
                )

                Icon(
                    painter = painterResource(R.drawable.nexticon),
                    contentDescription = "play",
                    Modifier
                        .size(80.dp)
                        .clickable(onClick = {
                            viewModel.nextTrack()
                        })
                )

            }

        }
    }


}