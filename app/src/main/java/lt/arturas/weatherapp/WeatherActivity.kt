package lt.arturas.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import lt.arturas.weatherapp.choose_city_fragment.ChooseCityFragment
import lt.arturas.weatherapp.city_details_fragment.CityDetailsFragment
import lt.arturas.weatherapp.city_forcast_fragment.CityForcastFragment
import lt.arturas.weatherapp.databinding.ActivityMainBinding

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openChooseCityFragment()
        }
    }

    private fun openChooseCityFragment() {
        setCurrentFragment(ChooseCityFragment.newInstance(), ChooseCityFragment.TAG, )
    }

    fun openCityDetailsFragment() {
        setCurrentFragment(CityDetailsFragment.newInstance(), CityDetailsFragment.TAG, true)
    }

    fun openCityForcastFragment() {
        setCurrentFragment(CityForcastFragment.newInstance(), CityForcastFragment.TAG, true)
    }

    private fun setCurrentFragment(fragment: Fragment, tag: String, addBackStack: Boolean = false) {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_container_view,
                fragment,
                tag
            )

            setReorderingAllowed(true)

            if (addBackStack){
                addToBackStack(tag)
            }
        }
    }
}