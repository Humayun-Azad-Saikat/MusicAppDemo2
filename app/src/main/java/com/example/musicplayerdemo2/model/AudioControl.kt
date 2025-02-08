package com.example.musicplayerdemo2.model

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
    fun release()



}