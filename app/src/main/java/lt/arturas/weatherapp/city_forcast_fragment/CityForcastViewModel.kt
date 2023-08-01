package lt.arturas.weatherapp.city_forcast_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.reqres.ApiServiceClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//class CityForcastViewModel : ViewModel() {
//    // TODO: Implement the ViewModel
//}

class CityForcastViewModel : ViewModel() {
//    private val _cityStateFlow: MutableStateFlow<CityForcastResponse?> =
//        MutableStateFlow(CityForcastResponse())
//
//
//    val cityStateFlow = _cityStateFlow.asStateFlow()
//
//    fun fetchCity(cityName: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = ApiServiceClient.providesApiService().getCityDetails(
//                cityName = cityName
//            )
//            _cityStateFlow.value = response.body()
//        }
//    }
}