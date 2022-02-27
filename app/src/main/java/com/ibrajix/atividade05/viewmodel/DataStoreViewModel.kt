package com.atividades.atividade05.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.atividades.atividade05.preferences.PreferenceStorage
import kotlinx.coroutines.launch

class DataStoreViewModel @ViewModelInject constructor(private val preferenceStorage: PreferenceStorage): ViewModel() {

    //saved key as liveData
    val savedKey = preferenceStorage.savedKey().asLiveData()
    fun setSavedKey(key: Boolean) {
        viewModelScope.launch {
            preferenceStorage.setSavedKey(key)
        }
    }

}