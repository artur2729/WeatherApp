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
import com.example.news.repository.reqres.CityDetailsResponse
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.choose_city_fragment.ChooseCityFragment
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding

class CityDetailsFragment : Fragment() {

    private val viewModel: CityDetailsViewModel by viewModels()
    //private var recyclerAdapter: CustomAdapter? = null

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
        //viewModel.fetchCity("Vilnius")

        observeCityStateFlow()
    }

//    private fun setUpRecyclerView() {
//        binding.cityRecyclerView.apply {
//            recyclerAdapter = CustomAdapter { source -> }
//            adapter = recyclerAdapter
//            layoutManager = LinearLayoutManager(activity)
//            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
//        }
//    }

    private fun bindCityWeather(city: CityDetailsResponse) {
        binding.apply {
            cityName.text = city.name
            temp.text = city.main.temp.toString()
            humidity.text = city.main.humidity.toString()
            windSpeed.text = city.wind.speed.toString()
        }
    }

    private fun submitCityDetails(city: CityDetailsResponse) {
            bindCityWeather(city)
    }

   //  https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

    private fun observeCityStateFlow() {
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

    private fun receiveDataFromChooseCityFragment() {
        setFragmentResultListener(ChooseCityFragment.REQUEST_KEY_CITY) { requestKey, bundle ->
            val cityName = bundle.getString(ChooseCityFragment.KEY_CITY_NAME, "")
            Log.i(TAG, "receiveDataFromChooseCityFragment: ${cityName}")
            viewModel.fetchCity(cityName)
        }
    }

    private fun transferDataToNewsDetailsFragment(city: CityDetailsResponse) {
        val bundle = bundleOf(REQUEST_KEY_CITY_DETAILS to city)
        setFragmentResult(KEY_CITY_NAME_DETAILS, bundle)
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
