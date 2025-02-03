package com.example.musicplayerdemo2.viewModel

import android.content.Context
import com.example.musicplayerdemo2.contentProvider.MusicContentResolver
import com.example.musicplayerdemo2.model.Audio
import javax.inject.Inject

class MusicRepository @Inject constructor() {

    @Inject
    lateinit var musicContentResolver: MusicContentResolver

    fun fetchAudioList(): List<Audio>{
       return musicContentResolver.getAudioList()
    }

}