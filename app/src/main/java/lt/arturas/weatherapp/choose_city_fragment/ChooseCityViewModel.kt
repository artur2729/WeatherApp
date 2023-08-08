package lt.arturas.weatherapp.choose_city_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.reqres.ApiServiceClient
import lt.arturas.weatherapp.repository.open_weather_map.CityDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChooseCityViewModel : ViewModel() {
    private val _chooseCityStateFlow: MutableStateFlow<CityDetailsResponse?> =
        MutableStateFlow(CityDetailsResponse())

    fun fetchCity(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiServiceClient.providesApiService().getCityDetails(cityName = cityName)
            _chooseCityStateFlow.value = response.body()
        }
    }
}