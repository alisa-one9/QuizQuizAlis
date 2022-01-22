package com.example.quizquizalis.presentation.fragments.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizquizalis.data.local.LocalRepository
import com.example.quizquizalis.data.local.RoomResultModel
import kotlinx.coroutines.launch

class QuizViewModel (private val localRepository: LocalRepository):ViewModel(){

    private val position = MutableLiveData<Int>()
    private val correctAns = MutableLiveData<Int>()

    private var pos = 0
    private var ans = 0

    fun getPosition(): Int {
        position.value = pos++
        return position.value!!
    }

    fun setCorrectAns() {
        correctAns.value = ++ans
    }
    fun getCorrectAns(): Int {
        return if (correctAns.value != null) correctAns.value!! else 0
    }
    fun insert(history: RoomResultModel) {
        viewModelScope.launch {
            localRepository.insert(history)
        }
    }

//    fun getAllQuestions(
//        amount: Int,
//        category: Int,
//        difficulty: String,
//        type: String
//    ): LiveData<Resource<QuizModel>> {
//        return repository.getAllQuestions(amount, category, difficulty, type)
//    }
//
//    fun saveQuestions(roomResult: RoomResultModel) = viewModelScope.launch {
//        repository.upsert(roomResult)
//    }
//
 //   fun getSavedQuestions() = repository.getSavedQuestions()
//
 //   fun deleteQuestions(roomResult: RoomResultModel) = viewModelScope.launch {
//        repository.deleteResult(roomResult)
//    }
}