package lt.arturas.weatherapp.repository.open_weather_map

import com.google.gson.annotations.SerializedName

data class CityDetails(
//    @SerializedName("weather")
//    val weather: List<Weather> = listOf(),
//    @SerializedName("main")
//    val main: Main = Main(),
    //@SerializedName("visibility")
    val visibility: Int,
//    @SerializedName("wind")
//    val wind: Wind = Wind(),
   // @SerializedName("name")
    val name: String,
)

//data class City(
//    val id: String,
//    val name: String,
//    val temp: String,
//    val humidity: String,
//    val speed: String
//)
