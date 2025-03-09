package com.example.musicplayerdemo2.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayerdemo2.model.Audio
import com.example.musicplayerdemo2.model.DataStorePrefUtils
import com.example.musicplayerdemo2.model.Media3Components
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioListScreenViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
    val media3Components: Media3Components,
    val dataStorePrefUtils: DataStorePrefUtils
): ViewModel() {


    private val _audioFiles = MutableLiveData<List<Audio>>()
    val audioFiles: LiveData<List<Audio>> = _audioFiles


//    private val _audioName = mutableStateOf("Unknown Track")
//    val audioName: State<String> get() = _audioName

    private var _isPlaying = mutableStateOf(true)
    val isPlaying: State<Boolean> get() = _isPlaying



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


    fun playAudio(){
        if (!media3Components.isPlaying()){
            media3Components.play()
            isPlaying()
           // getAudioName()
        }

    }

    fun pauseAudio(){
        if (media3Components.isPlaying()){
            media3Components.pause()
            isPlaying()
            //getAudioName()
        }
    }

    fun isPlaying(){
        _isPlaying.value = media3Components.isPlaying()
    }


    fun getAudioName(): String{
         return media3Components.getAudioName()
    }

    fun getCurrentAudio(mediaUri: String):String{
        return media3Components.getCurrentAudio(mediaUri)
    }

    fun getCurrentPlayingAudioUri(): String?{
        return media3Components.getCurrentPlayingAudioUri().toString()
    }

    fun getDuration(): Long{
        return media3Components.getDuration()
    }

    fun getCurrentSeekPosition(): Long{
        return media3Components.getCurrentSeekPositionTime()
    }

}

