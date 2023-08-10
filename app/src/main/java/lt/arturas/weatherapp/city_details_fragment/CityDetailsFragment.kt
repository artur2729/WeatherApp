package lt.arturas.weatherapp.city_details_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import lt.arturas.weatherapp.repository.open_weather_map.CityForcastResponse
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.R
import lt.arturas.weatherapp.WeatherActivity
import lt.arturas.weatherapp.choose_city_fragment.ChooseCityFragment
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding
import lt.arturas.weatherapp.repository.open_weather_map.CityDetailsResponse

class CityDetailsFragment : Fragment() {

    private val viewModel: CityDetailsViewModel by viewModels()

    private var _binding: FragmentCityDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiveDataFromChooseCityFragment()
        observeCityStateFlow()
    }

    private fun bindCityWeather(city: CityDetailsResponse) {
        if (city.name == "" ){
            binding.apply {
                cityName.text = "City not found"
            }
        }
        else {
            binding.apply {
                WeatherImageView.background = null
                if (city.weather[0].description == "broken clouds") {
                    WeatherImageView.setImageResource(R.drawable.cloud)
                } else if (city.weather[0].description == "light rain") {
                    WeatherImageView.setImageResource(R.drawable.cloud)
                } else if (city.weather[0].description == "overcast clouds") {
                    WeatherImageView.setImageResource(R.drawable.cloud)
                } else if (city.weather[0].description == "moderate rain") {
                    WeatherImageView.setImageResource(R.drawable.cloud)
                } else if (city.weather[0].description == "few clouds") {
                    WeatherImageView.setImageResource(R.drawable.cloud)
                } else if (city.weather[0].description == "heavy intensity rain") {
                    WeatherImageView.setImageResource(R.drawable.cloud)
                } else if (city.weather[0].description == "clear sky") {
                    WeatherImageView.setImageResource(R.drawable.sunny)
                } else if (city.weather[0].description == "scattered clouds") {
                    WeatherImageView.setImageResource(R.drawable.sunny)
                }
                cityName.text = city.name
                WeatherImageView.setImageResource(R.drawable.cloud)
                temp.text = "Temperature: " + (city.main.temp - 273.15).toFloat().toString() + " C"
                humidity.text = "Humidity: " + city.main.humidity.toString() + "%"
                windSpeed.text = "WindSpeed " + city.wind.speed.toString() + " meter/sec"
                detailsTextView.text = "Weather description: " + city.weather[0].description

            }
        }
    }

    private fun submitCityDetails(city: CityDetailsResponse) {
            bindCityWeather(city)
    }
    private fun onClickCityForcast() {
        //onclick Search button ->
        binding.forcastButton.setOnClickListener {
            val cityValue = (binding.cityName.text).toString()
            (activity as WeatherActivity).openCityForcastFragment()
            transferDataToCityForcastFragment(cityValue)
        }
    }


    private fun observeCityStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cityStateFlow.collect { response ->
                    if (response != null) {
                            submitCityDetails(response)

                        onClickCityForcast()
                    }
                }
            }
        }
    }

    private fun receiveDataFromChooseCityFragment() {
        setFragmentResultListener(ChooseCityFragment.REQUEST_KEY_CITY) { requestKey, bundle ->
            val cityName = bundle.getString(ChooseCityFragment.KEY_CITY_NAME, "")
            viewModel.fetchCity(cityName)
        }
    }

    private fun transferDataToCityForcastFragment(city: String) {
        val bundle = bundleOf(REQUEST_KEY_CITY_DETAILS to city)
        setFragmentResult(KEY_CITY_NAME_DETAILS, bundle)
        Log.i(TAG, "transferDataToCityForcastFragment: $city")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "city_detail_fragment"
        const val REQUEST_KEY_CITY_DETAILS = "city_fragment_result_key"
        const val KEY_CITY_NAME_DETAILS = "key_city_name"
        fun newInstance() = CityDetailsFragment()
    }
}
