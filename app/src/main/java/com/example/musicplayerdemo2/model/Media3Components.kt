package com.example.musicplayerdemo2.model

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject

class Media3Components @Inject constructor(context: Context): DefaultLifecycleObserver{ //extented lifecycle owner for observing the app life cycle like onDistroy the media player will realese

    private val exoPlayer = ExoPlayer.Builder(context).build()

    fun loadMedia(mediaUri: String){
        val mediaItem = MediaItem.fromUri(mediaUri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    fun play(){
        exoPlayer.play()
    }

    fun pause(){
        exoPlayer.pause()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        exoPlayer.release()
    }


}