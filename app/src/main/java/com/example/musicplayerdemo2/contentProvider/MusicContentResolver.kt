package com.example.musicplayerdemo2.contentProvider

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.example.musicplayerdemo2.model.Audio
import javax.inject.Inject

class MusicContentResolver @Inject constructor(val context: Context){

    private val projection = arrayOf(
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.DISPLAY_NAME,
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.ARTIST

    )

    private val selectionClause: String = "${MediaStore.Audio.AudioColumns.IS_MUSIC} = ? AND ${MediaStore.Audio.AudioColumns.MIME_TYPE} NOT IN (?,?,?,?)"
    private val selectionArgs = arrayOf("1","audio/amr","audio/mp3","audio/3gpp","audio/aag")
    private val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"


    private val audioList = mutableListOf<Audio>()

    private fun getCursorData(): MutableList<Audio>{

        context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selectionClause,
            selectionArgs,
            sortOrder
        ).use {cursor->

            val nameColumn = cursor?.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val idColumn = cursor?.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val durationColumn = cursor?.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val artistColumn = cursor?.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)

            cursor.apply {

                while (cursor?.moveToNext() == true) {

                    val id = cursor.getLong(idColumn!!)
                    val uri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    val name = cursor.getString(nameColumn!!)
                    val duration = cursor.getLong(durationColumn!!)
                    val artist = cursor.getString(artistColumn!!)

                    audioList.add(Audio(uri, name, id, duration, artist))

                }
            }


        }

        return audioList

    }


    fun getAudioList(): List<Audio>{
        return getCursorData()
    }



}