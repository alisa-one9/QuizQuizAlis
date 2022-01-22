package com.example.quizquizalis.data.remote

import com.example.quizquizalis.data.QuizModellDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("/api.php")
    suspend fun  getDefaultQuestions(
    @Query("amount") amount: String
    ):Response<QuizModellDto>

    @GET("/api.php")
    suspend fun getQuestionsWithCategory(
        @Query("amount") amount: String,
        @Query("category") category: String
    ): Response<QuizModellDto>

    @GET("/api.php")
    suspend fun getQuestionsWithDifficulty(
        @Query("amount") amount: String,
        @Query("difficulty") difficulty: String
    ): Response<QuizModellDto>

    @GET("/api.php")
    suspend fun getQuestionsWithAll(
        @Query("amount") amount: String,
        @Query("category") category: String,
        @Query("difficulty") difficulty: String
    ): Response<QuizModellDto>
}