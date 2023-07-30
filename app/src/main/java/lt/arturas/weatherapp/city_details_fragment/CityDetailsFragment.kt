package lt.arturas.weatherapp.city_details_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news.repository.reqres.CityDetailsResponse
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.R
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding

class CityDetailsFragment : Fragment() {

    private val viewModel: CityDetailsViewModel by viewModels()

    private var _binding: FragmentCityDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_city_details, container, false)

        _binding = FragmentCityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchNewsSources()

        observeNewsSourcesStateFlow()
    }

    private fun bindCityWeather(city: CityDetailsResponse) {
        binding.apply {
            cityName.text = city.name
            temp.text = city.main.temp.toString()
            //temp.text = city.main.temp.toString()
           // humidity.text = city.main.humidity.toString()
           // windSpeed.text = city.wind.speed.toString()
        }
    }

    //private fun submitCityDetails(city: MutableList<CityDetails>) {
    private fun submitCityDetails(city: CityDetailsResponse) {
            bindCityWeather(city)
    }

   //  https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

    private fun observeNewsSourcesStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cityStateFlow.collect { response ->
                    val list = response
//                    val list = response?.name

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
