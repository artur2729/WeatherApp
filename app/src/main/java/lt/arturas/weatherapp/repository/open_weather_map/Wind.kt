package lt.arturas.weatherapp.repository.open_weather_map

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    val speed: Double = 0.0,
    @SerializedName("deg")
    val deg: Double = 0.0
)