package lt.arturas.weatherapp.choose_city_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news.repository.reqres.CityDetailsResponse
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.R
import lt.arturas.weatherapp.WeatherActivity
import lt.arturas.weatherapp.city_forcast_fragment.CityForcastViewModel
import lt.arturas.weatherapp.databinding.FragmentChooseCityBinding

class ChooseCityFragment : Fragment() {

    private val viewModel: ChooseCityViewModel by viewModels()

    private var _binding: FragmentChooseCityBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName : String = "Vilnius"

        onClickCitySearch(cityName)

//        viewModel.fetchCity(cityName)
//        observeNewsSourcesStateFlow()
    }

    private fun onClickCitySearch(cityName: String) {
        //onclick Search button ->
        binding.searchButton.setOnClickListener{
            viewModel.fetchCity(cityName)
            observeNewsSourcesStateFlow()
        }
    }

    private fun observeNewsSourcesStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cityStateFlow.collect { response ->
//                    val list = response?.name

                    if (response?.cod == "404"){
                        Toast.makeText(activity, "City not found, please try again", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        if (response != null) {
                            (activity as WeatherActivity).openCityDetailsFragment()
                            transferDataToCityDetailFragment(response)
                        }
                    }
                }
            }
        }
    }

    private fun transferDataToCityDetailFragment(city: CityDetailsResponse) {
        val bundle = bundleOf(KEY_CITY_NAME to city.name)
        setFragmentResult(REQUEST_KEY_CITY, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "city_name_fragment"
        const val REQUEST_KEY_CITY = "city_fragment_result_key"
        const val KEY_CITY_NAME = "key_city_name"
        fun newInstance() = ChooseCityFragment()
    }
}