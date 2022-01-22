package com.example.quizquizalis.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RoomResultModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val category: String?,
    var correctAnswers: Int,
    val difficulty: String?,
    val amount:String?,
    val date: Long
        )
