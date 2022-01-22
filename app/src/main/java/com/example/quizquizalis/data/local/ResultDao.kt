package com.example.quizquizalis.data.local

import androidx.room.*

@Dao
interface ResultDao {
    @Query("select * from roomresultmodel")
    suspend fun getAllHistory(): List<RoomResultModel>

    @Query("delete from roomresultmodel")
    suspend fun  deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomResult: RoomResultModel)

    @Delete
    suspend fun delete(roomResult: RoomResultModel)

}