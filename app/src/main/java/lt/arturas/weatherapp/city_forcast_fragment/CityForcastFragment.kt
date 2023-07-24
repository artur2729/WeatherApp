package lt.arturas.weatherapp.city_forcast_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lt.arturas.weatherapp.R

class CityForcastFragment : Fragment() {

    companion object {
        fun newInstance() = CityForcastFragment()
    }

    private lateinit var viewModel: CityForcastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_forcast, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CityForcastViewModel::class.java)
        // TODO: Use the ViewModel
    }

}