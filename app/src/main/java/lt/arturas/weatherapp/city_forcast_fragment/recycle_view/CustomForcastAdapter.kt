package lt.arturas.weatherapp.choose_city_fragment.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import lt.arturas.weatherapp.repository.open_weather_map.CityDetailsResponse
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding

class CustomForcastAdapter(
    private val onClick: (CityDetailsResponse) -> Unit
) : ListAdapter<CityDetailsResponse, CustomForcastViewHolder>(
    Comparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomForcastViewHolder(
        FragmentCityDetailsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        onClick
    )

    override fun onBindViewHolder(holder: CustomForcastViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class Comparator : DiffUtil.ItemCallback<CityDetailsResponse>() {
        override fun areItemsTheSame(oldItem: CityDetailsResponse, newItem: CityDetailsResponse) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CityDetailsResponse, newItem: CityDetailsResponse) =
            oldItem == newItem
    }
}