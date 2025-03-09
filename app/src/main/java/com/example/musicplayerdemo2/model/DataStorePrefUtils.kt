package com.example.musicplayerdemo2.model

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class DataStorePrefUtils(val context: Context) {
    private val Context.dataStore by preferencesDataStore("track name")

    suspend fun saveTrackName(trackName: String){
        context.dataStore.edit {
            it[trackNameKey] = trackName
        }
    }

    suspend fun saveAudioUri(audioUri: String){
        context.dataStore.edit {
            it[audioUriKey] = audioUri
        }
    }

    suspend fun getTrackName():String?{
        return context.dataStore.data.map {
            it[trackNameKey]
        }.first()
    }

    suspend fun getAudioUri(): String?{
        return context.dataStore.data.map {
            it[audioUriKey]
        }.first()
    }



    companion object {
        private val trackNameKey = stringPreferencesKey("TrackName")
        private val audioUriKey = stringPreferencesKey("AudioUri")
    }


}