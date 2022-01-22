package com.example.quizquizalis.di


import com.example.quizquizalis.presentation.fragments.history.HistoryViewModel
import com.example.quizquizalis.presentation.fragments.home.HomeViewModel
import com.example.quizquizalis.presentation.fragments.quiz.QuizViewModel
import com.example.quizquizalis.presentation.fragments.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { HomeViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}