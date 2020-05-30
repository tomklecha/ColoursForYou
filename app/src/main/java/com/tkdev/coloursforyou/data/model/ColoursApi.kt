package com.tkdev.coloursforyou.data.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await

import retrofit2.converter.gson.GsonConverterFactory

interface ColoursApi {
    suspend fun fetchData(): List<String>
}

class ColoursApiDefault() : ColoursApi {

    private var service: ColourService

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://random-word-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        service = retrofit.create(ColourService::class.java)
    }

    override suspend fun fetchData(): List<String> {
        return service.getWordsList(5).await()
    }

}