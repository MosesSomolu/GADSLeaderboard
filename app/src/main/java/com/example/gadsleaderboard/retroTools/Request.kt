package com.example.gadsleaderboard.retroTools

import android.util.Log
import java.net.URL

class Request(private val url: String) {

    fun run() {
        val userListJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, userListJsonStr)
    }
}




