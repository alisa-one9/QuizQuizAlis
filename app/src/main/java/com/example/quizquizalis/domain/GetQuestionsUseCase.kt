package com.example.quizquizalis.domain

import androidx.lifecycle.LiveData
import com.example.quizquizalis.domain.model.QuizModell
import com.example.quizquizalis.core.network.Resource

class GetQuestionsUseCase (private val repository: RemoteRepository) {

    fun getAllQuestions(amount: String,
                        category: String,
                        difficulty: String): LiveData<Resource<QuizModell>>{
        return repository.getAllQuestions(amount, category, difficulty)
    }

}