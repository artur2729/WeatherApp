package lt.arturas.weatherapp.repository.open_weather_map

import com.google.gson.annotations.SerializedName


data class Main(
    @SerializedName("temp")
    val temp: Double = 0.0,
    @SerializedName("pressure")
    val pressure: Double = 0.0,
    @SerializedName("humidity")
    val humidity: Int = 0,
    @SerializedName("temp_min")
    val tempMin: Double = 0.0,
    @SerializedName("temp_max")
    val tempMax: Double = 0.0
)
