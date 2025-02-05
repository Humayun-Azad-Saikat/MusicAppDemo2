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
class MusicViewModel @Inject constructor(private val musicRepository: MusicRepository): ViewModel() {

    private val _audioFiles = MutableLiveData< List<Audio>>()
    val audioFiles: LiveData<List<Audio>> = _audioFiles

    @Inject
    lateinit var media3Components: Media3Components

    fun getMusic(){
        viewModelScope.launch{

            if (_audioFiles.value.isNullOrEmpty()){
                val audioList = musicRepository.fetchAudioList()
                _audioFiles.postValue(audioList)
            }

        }
    }


    fun loadMediaUri(mediaUri: String){
        media3Components.loadMedia(mediaUri)
    }

    fun playAudio(){
        media3Components.play()
    }

}