package com.example.musicplayerdemo2.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.browse.MediaBrowser
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.exoplayer.ExoPlayer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Media3Components @Inject constructor(context: Context): DefaultLifecycleObserver, AudioControl{ //extented lifecycle owner for observing the app life cycle like onDistroy the media player will realese

    private val exoPlayer = ExoPlayer.Builder(context).build()

    private val playList = mutableListOf<MediaItem>()
    private var currentIndex = -1



    override fun loadMediaPlaylist(mediaUris: List<String>, currentAudioUri: String){

        val currentAudio = getCurrentAudio(currentAudioUri.toString())
        Log.d("Media3Components", "currentAudio:$currentAudio")


        playList.clear()
        exoPlayer.setMediaItems(playList)
        mediaUris.forEach { mediaUri ->


            playList.add(MediaItem.fromUri(mediaUri))
            Log.d("Media3Components", "Added media: $mediaUri")

            if(currentAudio == mediaUri.toString()){
                loadMediaUri(mediaUri.toString())
                currentIndex = playList.size-1

                Log.d("Media3Components", "currentIndex: $currentIndex")
            }
            exoPlayer.setMediaItems(playList)
            exoPlayer.prepare()
            exoPlayer.seekTo(currentIndex, 0L)

        }



    }


    override fun loadMediaUri(mediaUri: String){
        val mediaItem = MediaItem.fromUri(mediaUri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    override fun getCurrentAudio(mediaUri: String): String{
        return mediaUri.toString()
    }


    override fun play(){
        exoPlayer.play()
    }

    override fun pause(){
        exoPlayer.pause()
    }

    override fun isPlaying(): Boolean{
       return exoPlayer.isPlaying
    }

    override fun release(){
        exoPlayer.release()
    }

    override fun nextTrack() {
        if (playList.isNotEmpty()) {

            currentIndex = (currentIndex + 1) % playList.size

            Log.d("media3Components", "$currentIndex")
             exoPlayer.seekTo(currentIndex,0L)
            exoPlayer.play()
        }
    }


    override fun previousTrack() {
        if (playList.isNotEmpty()) {

            currentIndex = if (currentIndex - 1 < 0) playList.size - 1 else currentIndex - 1

            exoPlayer.seekTo(currentIndex,0L)
            exoPlayer.play()
            Log.d("media3Components", "$currentIndex")
        }
    }

    override fun getCurrentSeekPositionTime(): Long{
        return exoPlayer.currentPosition
    }

    override fun getDuration(): Long{
        val duration = exoPlayer.duration
        return if (duration > 0) duration else 1L
    }

    override fun seekTo(position: Long){
        exoPlayer.seekTo(position)
    }

    override fun getAudioName(): String{
       // return (if(exoPlayer.mediaMetadata.title.toString() != null) else "Unknown Track").toString()
        val audioName = exoPlayer.mediaMetadata.title.toString()
        if(audioName.isNotEmpty() && exoPlayer.isPlaying){
            return audioName
        }
        else if(!exoPlayer.isPlaying){
            return ""
        }
        return "Unknown Track"
    }

    override fun getAlbum (): Bitmap? {
        val artworkData = exoPlayer.mediaMetadata.artworkData
        return artworkData?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        release()
    }


}