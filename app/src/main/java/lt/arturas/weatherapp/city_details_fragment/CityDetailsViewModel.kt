package lt.arturas.weatherapp.city_details_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import lt.arturas.weatherapp.repository.open_weather_map.CityForcastResponse
import com.example.news.repository.reqres.ApiServiceClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.repository.open_weather_map.CityDetailsResponse

class CityDetailsViewModel : ViewModel() {
    private val _cityStateFlow: MutableStateFlow<CityDetailsResponse?> =
        MutableStateFlow(CityDetailsResponse())


    val cityStateFlow = _cityStateFlow.asStateFlow()

    fun fetchCity(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiServiceClient.providesApiService().getCityDetails(
                cityName = cityName
            )
            _cityStateFlow.value = response.body()
        }
    }
}