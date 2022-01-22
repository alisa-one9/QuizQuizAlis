package com.example.quizquizalis.presentation.fragments.history
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.quizquizalis.data.local.LocalRepository
import com.example.quizquizalis.data.local.RoomResultModel

class HistoryViewModel (private val localRepository: LocalRepository): ViewModel(){
    val result = MutableLiveData<List<RoomResultModel>>()

    fun getAllHistories() {
        viewModelScope.launch {
            result.value = localRepository.getAllHistory()
        }
    }

    fun delete(history: RoomResultModel) {
        viewModelScope.launch {
            localRepository.delete(history)
        }
    }
}