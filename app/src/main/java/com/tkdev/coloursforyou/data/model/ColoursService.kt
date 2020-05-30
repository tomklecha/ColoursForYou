package com.tkdev.coloursforyou.data.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ColourService {
    @GET("word?")
    fun getWordsList(@Query("number") wordCount: Int): Call<List<String>>
}