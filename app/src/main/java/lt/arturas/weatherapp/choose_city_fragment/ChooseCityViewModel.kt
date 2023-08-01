package lt.arturas.weatherapp.choose_city_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.reqres.ApiServiceClient
import com.example.news.repository.reqres.CityDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChooseCityViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    //    private val _cityStateFlow: MutableStateFlow<CityDetailsResponse?> =
//        MutableStateFlow(CityDetailsResponse())
    private val _cityStateFlow: MutableStateFlow<CityDetailsResponse?> =
        MutableStateFlow(CityDetailsResponse())


    val cityStateFlow = _cityStateFlow.asStateFlow()

    fun fetchCity(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiServiceClient.providesApiService().getCityDetails(cityName = cityName)
            _cityStateFlow.value = response.body()
        }
    }
}