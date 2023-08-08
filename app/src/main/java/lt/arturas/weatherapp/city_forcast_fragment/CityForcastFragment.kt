package lt.arturas.weatherapp.city_forcast_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.R
import lt.arturas.weatherapp.WeatherActivity
import lt.arturas.weatherapp.choose_city_fragment.ChooseCityFragment
import lt.arturas.weatherapp.city_details_fragment.CityDetailsFragment
import lt.arturas.weatherapp.databinding.FragmentCityForcastBinding
import lt.arturas.weatherapp.repository.open_weather_map.CityDetailsResponse
import lt.arturas.weatherapp.repository.open_weather_map.CityForcastResponse

class CityForcastFragment : Fragment() {

    private val viewModel: CityForcastViewModel by viewModels()

    private var _binding: FragmentCityForcastBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityForcastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiveDataFromCityForcastFragment()
        observeCityStateFlow()
    }

    private fun bindCityWeather(city: CityForcastResponse) {
        if (city.message == "city not found" ){
            binding.apply {
                dateTextView.text = "City not found"
            }
        }
        if(city.message == "" && city.cod == ""){
            binding.apply {
                dateTextView.text = "City not found, empty response"
            }
        }
        else {
            binding.apply {
                dateTextView.text = city.list[0].dt_txt
                tempTextView.text = "Temperature: " + (city.list[0].main.temp - 273.15).toFloat().toString() + " C"
                descriptionTextView.text = "Weather description: " + city.list[0].weather[0].description

            }
        }
    }

    private fun submitCityForcast(city: CityForcastResponse) {
        bindCityWeather(city)
    }

    private fun observeCityStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cityForcastFlow.collect { response ->
                    if (response != null) {
                        Log.i(TAG, "observeCityStateFlow: $response")
                        submitCityForcast(response)
                    }
                }
            }
        }
    }

    private fun receiveDataFromCityForcastFragment() {
        setFragmentResultListener(CityDetailsFragment.REQUEST_KEY_CITY_DETAILS) { requestKey, bundle ->
            val cityName = bundle.getString(CityDetailsFragment.KEY_CITY_NAME_DETAILS, "")
            Log.i(CityDetailsFragment.TAG, "receiveDataFromCityForcastFragment: ${cityName}")
            viewModel.fetchCityForcast(cityName)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "city_forcast_fragment"

        fun newInstance() = CityForcastFragment()
    }
}