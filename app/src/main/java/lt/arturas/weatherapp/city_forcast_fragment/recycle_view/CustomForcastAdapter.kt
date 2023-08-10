package lt.arturas.weatherapp.choose_city_fragment.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import lt.arturas.weatherapp.repository.open_weather_map.CityForcastResponse
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding
import lt.arturas.weatherapp.databinding.FragmentCityForcastBinding
import lt.arturas.weatherapp.databinding.FragmentCityForcastListBinding
import lt.arturas.weatherapp.repository.open_weather_map.Forcast

class CustomForcastAdapter(
    private val onClick: (Forcast) -> Unit
) : ListAdapter<Forcast, CustomForcastViewHolder>(
    Comparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomForcastViewHolder(
        FragmentCityForcastBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        onClick
    )

    override fun onBindViewHolder(holder: CustomForcastViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class Comparator : DiffUtil.ItemCallback<Forcast>() {
        override fun areItemsTheSame(oldItem: Forcast, newItem: Forcast) =
            oldItem.dt_txt == newItem.dt_txt

        override fun areContentsTheSame(oldItem: Forcast, newItem: Forcast) =
            oldItem == newItem
    }
}