package com.weathernexus.client.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.weathernexus.client.R
import com.weathernexus.client.databinding.ItemCityWeatherBinding
import com.weathernexus.client.model.Constants.FORMATTING.Companion.IMAGE_FORMAT
import com.weathernexus.client.model.Constants.URL_CONSTANTS.Companion.IMG_URL
import com.weathernexus.client.model.Extensions.Companion.kelvinToCelsius
import com.weathernexus.client.model.Extensions.Companion.roundToTwoDecimalPlaces
import com.weathernexus.client.model.dataclass.current_weather.CurrentWeatherResponse

class ItemCWAdapter(
    var data: ArrayList<CurrentWeatherResponse>
): RecyclerView.Adapter<ItemCWAdapter.ItemHolder>() {

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemCityWeatherBinding.bind(itemView)

        fun bind(item: CurrentWeatherResponse) = with(itemView){
            binding.apply {
                tvCityName.text = item.name
                tvAvgTemp.text = String.format(resources.getString(R.string.tvAvgTemp), item.main!!.temp!!.kelvinToCelsius().roundToTwoDecimalPlaces().toString())
                tvMinTemp.text = String.format(resources.getString(R.string.tvMinTemp), item.main.temp_min!!.kelvinToCelsius().roundToTwoDecimalPlaces().toString())
                tvMaxTemp.text = String.format(resources.getString(R.string.tvMaxTemp), item.main.temp_max!!.kelvinToCelsius().roundToTwoDecimalPlaces().toString())

                val imageUrl = IMG_URL+item.weather!!.get(0).icon+IMAGE_FORMAT

                Glide.with(itemView.context)
                    .load(imageUrl)
                    .into(ivIconWeather)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_weather, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data.get(position))
    }


}