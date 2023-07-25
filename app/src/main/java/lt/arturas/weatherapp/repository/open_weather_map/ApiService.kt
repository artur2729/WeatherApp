package com.example.news.repository.reqres

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getCityDetails(
        @Query("q") cityName: String = "Vilnius",   // get city info
        @Query("appid") apiKey: String = "3df1f89b4d97610286525fa6a6124f0c",  //3df1f89b4d97610286525fa6a6124f0c  //my 8c417e3c53fe5ba3391fcca4c12155c0
    ): Response<CityDetailsResponse>

    @GET("data/2.5/weather")   //find city
    suspend fun searchCities(
        @Query("q") cityName: String = "Vilnius",
        @Query("appid") apiKey: String = "3df1f89b4d97610286525fa6a6124f0c"
    )
}