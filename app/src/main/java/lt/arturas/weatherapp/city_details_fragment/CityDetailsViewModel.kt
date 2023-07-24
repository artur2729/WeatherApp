package lt.arturas.weatherapp.city_details_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.reqres.CityDetailsResponse
import com.example.news.repository.reqres.ApiServiceClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityDetailsViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _cityStateFlow: MutableStateFlow<CityDetailsResponse?> =
        MutableStateFlow(CityDetailsResponse())


    val cityStateFlow = _cityStateFlow.asStateFlow()

    fun fetchNewsSources() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiServiceClient.providesApiService().getCityDetails()
            _cityStateFlow.value = response.body()
        }
    }
}