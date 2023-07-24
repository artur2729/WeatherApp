package lt.arturas.weatherapp.city_details_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.R
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding
import lt.arturas.weatherapp.repository.open_weather_map.CityDetails
import lt.arturas.weatherapp.repository.open_weather_map.Main
import lt.arturas.weatherapp.repository.open_weather_map.Wind

class CityDetailsFragment : Fragment() {

    private val viewModel: CityDetailsViewModel by viewModels()

    private var _binding: FragmentCityDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_details, container, false)

        _binding = FragmentCityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchNewsSources()

        observeNewsSourcesStateFlow()
    }

    private fun bindCityWeather(city: CityDetails, main: Main, wind: Wind) {
        binding.apply {
            cityName.text = city.name
            temp.text = main.temp.toString()
            humidity.text = main.humidity.toString()
            windSpeed.text = wind.speed.toString()
        }
    }

    private fun submitCityDetails(city: MutableList<CityDetails>) {
        for (i in 0 until city.size) {
            bindCityWeather(city[i])
        }
    }

   //  https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

    private fun observeNewsSourcesStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cityStateFlow.collect { response ->
                    val list = response?.cities

                    if (list != null) {
                        submitCityDetails(list)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "city_detail_fragment"
        fun newInstance() = CityDetailsFragment()
    }
}
