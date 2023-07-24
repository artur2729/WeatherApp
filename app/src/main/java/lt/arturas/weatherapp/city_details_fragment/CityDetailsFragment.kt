package lt.arturas.weatherapp.city_details_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import lt.arturas.weatherapp.R
import lt.arturas.weatherapp.city_forcast_fragment.CityForcastViewModel
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding

class CityDetailsFragment : Fragment() {

    private val viewModel: CityForcastViewModel by viewModels()

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

    companion object {
        fun newInstance() = CityDetailsFragment()
    }
}
