package com.example.gadsleaderboard.retroTools

import com.example.gadsleaderboard.models.RequestResult
import retrofit2.Call
import retrofit2.http.GET

interface Service {
        // "suspend" added because Kotlin coroutines are being used.
        @GET("/api/hours")
        suspend fun getHours(): RequestResult

        @GET("/api/skilliq")
        suspend fun getSkills(): RequestResult
}