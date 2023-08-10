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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import lt.arturas.weatherapp.choose_city_fragment.recycle_view.CustomForcastAdapter
import lt.arturas.weatherapp.city_details_fragment.CityDetailsFragment
import lt.arturas.weatherapp.databinding.FragmentCityForcastListBinding
import lt.arturas.weatherapp.repository.open_weather_map.CityForcastResponse
import lt.arturas.weatherapp.repository.open_weather_map.Forcast

class CityForcastFragment : Fragment() {

    private val viewModel: CityForcastViewModel by viewModels()

    private var _binding: FragmentCityForcastListBinding? = null  //FragmentCityForcastBinding
    private var recyclerAdapter: CustomForcastAdapter? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityForcastListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeCityStateFlow()
        receiveDataFromCityForcastFragment()
    }

    private fun setUpRecyclerView() {
        binding.forcastRecyclerView.apply {
            recyclerAdapter = CustomForcastAdapter { forcast -> onForcastCLick(forcast) } //forcast -> onArticleCLick(forcast)
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun onForcastCLick(forcast: Forcast) {
        Log.i(TAG, "onForcastCLick: getting $forcast")
    }

    private fun observeCityStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cityForcastFlow.collect { response ->
                    if (response != null) {
                        Log.i(TAG, "observeCityStateFlow: $response")
                        //submitCityForcast(response)
                        val list = response?.list

                        if (list != null) {
                            submitForcastList(list)
                        }
                    }
                }
            }
        }
    }

    private fun submitForcastList(list: List<Forcast>) {
        recyclerAdapter?.submitList(list)
        binding.forcastRecyclerView.adapter = recyclerAdapter
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