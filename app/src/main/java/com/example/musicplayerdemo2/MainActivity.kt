package com.example.musicplayerdemo2

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import com.example.musicplayerdemo2.model.Media3Components
import com.example.musicplayerdemo2.ui.theme.MusicPlayerDemo2Theme
import com.example.musicplayerdemo2.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val musicViewModel: MusicViewModel by viewModels()

    @Inject
    lateinit var media3Components: Media3Components

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicPlayerDemo2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    AudioShow(musicViewModel,media3Components)

                }
            }
        }
    }
}

@Composable
fun AudioShow(viewModel: MusicViewModel = hiltViewModel(),media3Components: Media3Components){

    val audioFiles = viewModel.audioFiles.observeAsState(initial = emptyList())
    viewModel.getMusic()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(audioFiles.value){
            Card(
                modifier = Modifier.fillMaxSize().height(80.dp).padding(0.dp,6.dp,0.dp,0.dp)
                    .clickable(onClick = {
                        media3Components.loadMedia(it.uri.toString())
                        media3Components.play()
                    }),
                colors = CardDefaults.cardColors(containerColor = Color.Cyan),
            ) {
                Text("${it.name}", color = Color.Blue, textAlign = TextAlign.Center)
            }
        }
    }

}