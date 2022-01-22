package com.example.quizquizalis.di

import android.content.Context
import androidx.room.Room
import com.example.quizquizalis.data.local.ResultDao
import com.example.quizquizalis.data.local.ResultDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private fun creatDataBase(context: Context) =
    Room.databaseBuilder(
        context.applicationContext,
        ResultDataBase::class.java,
        "article_db.db"
    )
        .build()


private fun getResultDao(resultDataBase: ResultDataBase): ResultDao {
    return resultDataBase.getResultDao()
}
var roomModule: Module = module {
    single { creatDataBase(androidContext()) }
    single { getResultDao(get()) }
}