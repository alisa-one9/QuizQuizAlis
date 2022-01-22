package com.example.quizquizalis.presentation.fragments.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizquizalis.data.local.LocalRepository
import kotlinx.coroutines.launch

class SettingsViewModel(private  val localRepository:LocalRepository)
    : ViewModel() {

    fun deleteAll(){
        viewModelScope.launch {
            localRepository.deleteAll()
        }
    }
}