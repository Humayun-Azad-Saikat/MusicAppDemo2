package com.example.musicplayerdemo2.uiLayer

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.twotone.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "play",
                    Modifier.size(80.dp)
                )

                Icon(
                    imageVector = Icons.Filled.,
                    contentDescription = "play",
                    Modifier.size(80.dp)
                )

                Icon(
                    imageVector = Icons.TwoTone.N,
                    contentDescription = "play",
                    Modifier.size(80.dp)
                )

            }

        }
    }


}