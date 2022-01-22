package com.example.quizquizalis.di

import com.example.quizquizalis.domain.GetQuestionsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetQuestionsUseCase(get()) }

}

