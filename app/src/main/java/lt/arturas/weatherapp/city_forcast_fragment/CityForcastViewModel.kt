package lt.arturas.weatherapp.city_forcast_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.reqres.ApiServiceClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.repository.open_weather_map.CityForcastResponse

class CityForcastViewModel : ViewModel() {
    private val _cityForcastFlow: MutableStateFlow<CityForcastResponse?> =
        MutableStateFlow(CityForcastResponse())


    val cityForcastFlow = _cityForcastFlow.asStateFlow()

    fun fetchCityForcast(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiServiceClient.providesApiService().getCityForcast(
                cityName = cityName
            )
            _cityForcastFlow.value = response.body()
        }
    }
}