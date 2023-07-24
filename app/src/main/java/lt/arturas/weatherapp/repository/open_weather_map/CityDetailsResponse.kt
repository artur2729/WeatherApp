package com.example.news.repository.reqres

import lt.arturas.weatherapp.repository.open_weather_map.CityDetails

data class CityDetailsResponse(
    val status: String = "",
    val cities: MutableList<CityDetails> = mutableListOf()
)