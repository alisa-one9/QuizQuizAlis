package com.example.quizquizalis.domain.model

data class  Resultt(
    val category: String?,
    val correct_answer: String?,
    val difficulty: String?,
    val incorrect_answers: List<String>?,
    val question: String?,
    val type: String?
)