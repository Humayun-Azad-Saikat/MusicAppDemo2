package com.example.musicplayerdemo2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayerdemo2.model.Audio
import com.example.musicplayerdemo2.model.Media3Components
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioListScreenViewModel @Inject constructor(private val musicRepository: MusicRepository): ViewModel() { //viewmodel for audioListScreen

    private val _audioFiles = MutableLiveData<List<Audio>>()
    val audioFiles: LiveData<List<Audio>> = _audioFiles

    @Inject
    lateinit var media3Components: Media3Components //for playerScreen

    fun getCurrentPlayingUri(uri: String): String?{
        return media3Components.getCurrentAudio(uri)
    }


    //audio content resolver handle //for audioListScreen
    fun getMusic(){
        viewModelScope.launch{

            if (_audioFiles.value.isNullOrEmpty()){
                val audioList = musicRepository.fetchAudioList()
                _audioFiles.postValue(audioList)
            }

        }
    }

//    fun deleteAudio(uri: Uri){
//        viewModelScope.launch{
//            musicContentResolver.delete(uri)
//        }
//    }



    fun loadPlaylist(mediaUri: List<String>,currentAudio: String){
        media3Components.loadMediaPlaylist(mediaUri,currentAudio)
    }

    fun loadMediaUri(mediaUri: String){
        media3Components.loadMediaUri(mediaUri)
    }

    fun playAudio(){
        media3Components.play()
    }

    fun getCurrentAudio(mediaUri: String):String{
        return media3Components.getCurrentAudio(mediaUri)
    }

    fun getCurrentPlayingAudioUri(): String?{
        return media3Components.getCurrentPlayingAudioUri().toString()
    }

}