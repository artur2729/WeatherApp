package lt.arturas.weatherapp.choose_city_fragment.recycle_view

import androidx.recyclerview.widget.RecyclerView
import lt.arturas.weatherapp.repository.open_weather_map.CityDetailsResponse
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding

class CustomForcastViewHolder(
    private val binding: FragmentCityDetailsBinding,
    private val onClick: (CityDetailsResponse) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentCity: CityDetailsResponse? = null

    init {
        binding.root.setOnClickListener { currentCity?.let { result -> onClick(result) } }
    }

    fun bind(city: CityDetailsResponse) {
        currentCity = city
        binding.apply {
            cityName.text = city.name
            temp.text = city.main.temp.toString()
            humidity.text = city.main.humidity.toString()
            windSpeed.text = city.wind.speed.toString()
        }
    }
}