package com.example.quizquizalis.domain

import com.example.quizquizalis.data.QuizModellDto
import com.example.quizquizalis.domain.model.QuizModell
import com.example.quizquizalis.domain.model.Resultt


class QuestionsMapper {

    fun mapQuizModellDtoToQuizModell(questionsDto: QuizModellDto?): QuizModell {
        return QuizModell(questionsDto?.response_code, mapListResultDto(questionsDto?.results))
    }
    private fun mapResultDto(resultDto: Resultt) = Resultt(
        resultDto.category,
        resultDto.correct_answer,
        resultDto.difficulty,
        resultDto.incorrect_answers,
        resultDto.question,
        resultDto.type,
    )
    private fun mapListResultDto(list: List<Resultt>?) = list?.map {
        mapResultDto(it)
    }


}