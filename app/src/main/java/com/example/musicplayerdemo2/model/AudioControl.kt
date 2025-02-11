package com.example.musicplayerdemo2.model

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

interface AudioControl {

   // fun loadMedia(mediaUris: String)
   fun loadMediaPlaylist(mediaUris: List<String>,currentAudioUri: String)
    fun loadMediaUri(mediaUri: String)
    fun getCurrentAudio(mediaUri: String): String
    fun play()
    fun pause()
    fun nextTrack()
    fun previousTrack()
    fun isPlaying(): Boolean
    fun getCurrentSeekPositionTime(): Long
    fun getDuration(): Long
    fun seekTo(position: Long)
    fun getAudioName(): String
    fun getFilePathFromContentUri(context: Context, contentUri: Uri): String?
    fun getAlbum (): Bitmap?
    fun release()



}