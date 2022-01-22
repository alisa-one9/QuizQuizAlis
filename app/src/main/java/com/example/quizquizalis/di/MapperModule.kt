package com.example.quizquizalis.di

import com.example.quizquizalis.domain.QuestionsMapper
import org.koin.dsl.module

val mapperModule = module {
     single { QuestionsMapper()}
}
