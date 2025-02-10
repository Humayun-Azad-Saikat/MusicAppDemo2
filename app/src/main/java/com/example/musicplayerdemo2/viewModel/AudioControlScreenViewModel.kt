package com.example.musicplayerdemo2.viewModel

import android.graphics.Bitmap
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

    fun getDuration(): Long{
        return media3Components.getDuration()
    }

    fun getCurrentSeekPosition(): Long{
        return media3Components.getCurrentSeekPositionTime()
    }

    fun seekTo(position: Long){
        media3Components.seekTo(position)
    }

    fun getAudioName(): String{
        return media3Components.getAudioName()
    }

    fun getAlbum():Bitmap?{
        return media3Components.getAlbum()
    }

    fun formatTime(milliSeconds: Long): String{
        val seconds = (milliSeconds/1000)%60
        val munites = (milliSeconds/(1000*60))%60
        val hour = (milliSeconds/(1000)*60*60)%24
        return if (hour>0) String.format("%02d:%02d:%02d",hour,munites,seconds) else String.format("%02d:%02d",munites,seconds)
    }

}