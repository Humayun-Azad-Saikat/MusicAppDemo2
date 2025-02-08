package com.example.musicplayerdemo2.viewModel

import androidx.lifecycle.ViewModel
import com.example.musicplayerdemo2.model.Media3Components
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AudioControlScreenViewModel @Inject constructor(val media3Components: Media3Components): ViewModel() {

    //audio control handle //for playerScreen

    fun playAudio(){
        if (!media3Components.isPlaying()){
            media3Components.play()
        }

    }

    fun pauseAudio(){
        if (media3Components.isPlaying()){
            media3Components.pause()
        }
    }

    fun previousTrack(){
        media3Components.previousTrack()
    }

    fun nextTrack(){
        media3Components.nextTrack()
    }

}