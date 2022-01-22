package com.example.quizquizalis.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.quizquizalis.data.QuizModellDto
import com.example.quizquizalis.domain.QuestionsMapper
import com.example.quizquizalis.domain.model.QuizModell
import com.example.quizquizalis.domain.RemoteRepository
import com.example.quizquizalis.core.network.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class RemoteRepositoryImpl (private val api:QuizApi, private val questionsMapper: QuestionsMapper) :
    RemoteRepository {

    override fun getAllQuestions(amount: String,
                                 category: String,
                                 difficulty: String,
                                 ): LiveData<Resource<QuizModell>> = liveData(Dispatchers.IO) {
        emit (Resource.loading(null))

        val response: Response<QuizModellDto> =
            if (category == "All" && difficulty == "All") {
                api.getDefaultQuestions(amount)
            } else if (category != "All" && difficulty == "All") {
                api.getQuestionsWithCategory(amount, category)
            } else if (category == "All" && difficulty != "All") {
                api.getQuestionsWithDifficulty(amount, difficulty)
            } else {
                api.getQuestionsWithAll(amount, category, difficulty)
            }

        emit(
            if (response.isSuccessful)
                Resource.success(questionsMapper.mapQuizModellDtoToQuizModell(response.body()), response.code())
            else
                Resource.error(response.message(),questionsMapper.mapQuizModellDtoToQuizModell(response.body()) , response.code())
        )
    }

}