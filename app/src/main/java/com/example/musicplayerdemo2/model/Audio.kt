package com.example.musicplayerdemo2.model

import android.net.Uri

data class Audio(
    val uri: Uri,
    val name: String,
    val id: Long,
    val duration: Long,
    val artist: String
)