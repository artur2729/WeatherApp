package lt.arturas.weatherapp.choose_city_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lt.arturas.weatherapp.R

class ChooseCityFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseCityFragment()
    }

    private lateinit var viewModel: ChooseCityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_city, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChooseCityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}