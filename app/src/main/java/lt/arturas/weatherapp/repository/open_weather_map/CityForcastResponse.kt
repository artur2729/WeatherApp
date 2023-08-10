package lt.arturas.weatherapp.repository.open_weather_map

import com.google.gson.annotations.SerializedName

data class CityForcastResponse(
    @SerializedName("message")
    val message: String = "",
    @SerializedName("cod")
    val cod: String = "",
    @SerializedName("cnt")
    val cnt: String = "",
    @SerializedName("list")
    val list: MutableList<Forcast> = mutableListOf()
)

data class Forcast(
    @SerializedName("main")
    val main: MainForcast = MainForcast(0.00),
    @SerializedName("weather")
    val weather: List<WeatherForcast> = listOf(WeatherForcast("")),
    @SerializedName("dt_txt")
    val dt_txt: String
)

data class MainForcast(
    @SerializedName("temp")
    val temp: Double
)

data class WeatherForcast(
    @SerializedName("description")
    val description: String
)