package lt.arturas.weatherapp.choose_city_fragment.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.news.repository.reqres.CityDetailsResponse
import lt.arturas.weatherapp.databinding.FragmentCityDetailsBinding

class CustomAdapter(
    private val onClick: (CityDetailsResponse) -> Unit
) : ListAdapter<CityDetailsResponse, CustomViewHolder>(
    Comparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(
        FragmentCityDetailsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        onClick
    )

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class Comparator : DiffUtil.ItemCallback<CityDetailsResponse>() {
        override fun areItemsTheSame(oldItem: CityDetailsResponse, newItem: CityDetailsResponse) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CityDetailsResponse, newItem: CityDetailsResponse) =
            oldItem == newItem
    }
}