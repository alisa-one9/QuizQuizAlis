package com.example.quizquizalis.di

import com.example.quizquizalis.data.local.LocalRepository
import com.example.quizquizalis.data.remote.RemoteRepositoryImpl
import com.example.quizquizalis.domain.RemoteRepository
import org.koin.dsl.module

val repoModules = module {
    single { RemoteRepositoryImpl(get() ,questionsMapper = get()) }
    single { LocalRepository(get()) }
    single { provideRemoteRepository(get()) }
}


private fun provideRemoteRepository(repoImpl: RemoteRepositoryImpl): RemoteRepository {
    return repoImpl
}