package com.example.news.repository.reqres

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//  https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getCityDetails(
        @Query("q") cityName: String = "Vilnius",   // get city info
        @Query("appid") apiKey: String = "8c417e3c53fe5ba3391fcca4c12155c0",
    ): Response<CityDetailsResponse>
}