package com.example.quizquizalis.data.local


class LocalRepository (private val historyDao: ResultDao) {

    suspend fun getAllHistory(): List<RoomResultModel> {
        return historyDao.getAllHistory()
    }

    suspend fun deleteAll() {
        historyDao.deleteAll()
    }

    suspend fun insert(history: RoomResultModel) {
        historyDao.insert(history)
    }

    suspend fun delete(history: RoomResultModel) {
        historyDao.delete(history)
    }
}