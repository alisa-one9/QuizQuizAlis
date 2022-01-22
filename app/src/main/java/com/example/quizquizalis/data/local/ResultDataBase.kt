package com.example.quizquizalis.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [RoomResultModel::class],version =1)

abstract class ResultDataBase : RoomDatabase(){

    abstract fun getResultDao():ResultDao
}