package com.example.gadsleaderboard.retroTools

import android.util.Log
import com.example.gadsleaderboard.models.RequestResult
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataRetriever {
    private val service : Service

    companion object {
        const val BASE_URL = "https://gadsapi.herokuapp.com"
    }

    init {
        val retrofit = Retrofit.Builder()

            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(Service::class.java)
    }

    // "suspend" added because Kotlin coroutines are being used.
    suspend fun getHours() : RequestResult {
        return service.getHours()
    }

    suspend fun getSkill() : RequestResult {
        return service.getSkills()
    }
}