package com.example.news.repository.reqres

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//  https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
object ApiServiceClient {
    private const val BASE_URL = "https://api.openweathermap.org/"
    fun providesApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .build()
            .create(ApiService::class.java)
    }
}