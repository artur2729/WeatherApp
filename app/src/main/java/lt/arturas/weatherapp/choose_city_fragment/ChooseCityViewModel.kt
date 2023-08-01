package lt.arturas.weatherapp.choose_city_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.reqres.ApiServiceClient
import com.example.news.repository.reqres.CityDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChooseCityViewModel : ViewModel() {
    private val _chooseCityStateFlow: MutableStateFlow<CityDetailsResponse?> =
        MutableStateFlow(CityDetailsResponse())


    val chooseCityStateFlow = _chooseCityStateFlow.asStateFlow()

    fun fetchCity(cityName : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiServiceClient.providesApiService().getCityDetails(cityName = cityName)
            _chooseCityStateFlow.value = response.body()
        }
    }
}