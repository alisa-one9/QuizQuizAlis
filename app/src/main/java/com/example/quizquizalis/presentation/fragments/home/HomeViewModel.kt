package com.example.quizquizalis.presentation.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizquizalis.domain.model.QuizModell
import com.example.quizquizalis.domain.GetQuestionsUseCase
import com.example.quizquizalis.core.network.Resource

class HomeViewModel(private val useCase: GetQuestionsUseCase):ViewModel() {

    val loadingProgressBar = MutableLiveData<Boolean>()

    fun getQuestions(
        amount: String,
        category: String,
        difficulty: String
    ): LiveData<Resource<QuizModell>> {
        return useCase.getAllQuestions(amount, category, difficulty)
    }
}