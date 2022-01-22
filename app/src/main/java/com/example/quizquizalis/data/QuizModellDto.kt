package com.example.quizquizalis.data

import com.example.quizquizalis.domain.model.Resultt

data class QuizModellDto(
    val response_code: Int?,
    val results: List<Resultt>?
)
