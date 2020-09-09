package com.example.gadsleaderboard.models

data class RequestResult(val users: List<Users>)

data class Users(
    val fullName: String?,
    val skillIq: Int,
    val learningHours: Int,
    val country : String
    )