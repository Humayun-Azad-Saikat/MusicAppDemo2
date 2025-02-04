package com.example.musicplayerdemo2.uiLayer

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicplayerdemo2.R


@Preview(showBackground = true)
@Composable
fun PlayerScreen(){

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
                    Modifier.size(80.dp),
                )

                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "play",
                    Modifier.size(80.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.nexticon),
                    contentDescription = "play",
                    Modifier.size(80.dp)
                )

            }

        }
    }


}