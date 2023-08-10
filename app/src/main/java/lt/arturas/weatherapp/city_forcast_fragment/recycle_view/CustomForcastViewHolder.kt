package lt.arturas.weatherapp.choose_city_fragment.recycle_view

import androidx.recyclerview.widget.RecyclerView
import lt.arturas.weatherapp.repository.open_weather_map.CityForcastResponse
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding
import lt.arturas.weatherapp.databinding.FragmentCityForcastBinding
import lt.arturas.weatherapp.databinding.FragmentCityForcastListBinding
import lt.arturas.weatherapp.repository.open_weather_map.Forcast

class CustomForcastViewHolder(
    private val binding: FragmentCityForcastBinding,
    private val onClick: (Forcast) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentCity: Forcast? = null

    init {
        binding.root.setOnClickListener { currentCity?.let { result -> onClick(result) } }
    }

    fun bind(forcast: Forcast) {
        currentCity = forcast
        binding.apply {
            if (forcast.dt_txt == "") {
                binding.apply {
                    dateTextView.text = "City not found"
                }
            }
            else {
                binding.apply {

                    dateTextView.text = forcast.dt_txt
                    tempTextView.text =
                        "Temperature: " + (forcast.main.temp - 273.15).toFloat()
                            .toString() + " C"
                    descriptionTextView.text =
                        "Weather description: " + forcast.weather[0].description

                }
            }
        }
    }
}