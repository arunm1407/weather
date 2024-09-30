package com.example.weather.presentation.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.data.enitity.WeatherMetadata
import com.example.weather.databinding.LayoutDetailItemBinding

/**
 * Created by Arun @ak - 14213  on 30/09/24.
 */
class DetailViewAdaptor : ListAdapter<WeatherMetadata, DetailViewAdaptor.ViewHolder>(DIFF_UTIL) {


    object DIFF_UTIL : DiffUtil.ItemCallback<WeatherMetadata>() {
        override fun areItemsTheSame(oldItem: WeatherMetadata, newItem: WeatherMetadata): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WeatherMetadata,
            newItem: WeatherMetadata
        ): Boolean {
            return oldItem == newItem
        }
    }


    inner class ViewHolder(private val binding: LayoutDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: WeatherMetadata) {
            binding.apply {
                min.text = "Min value : ${data.minValue}"
                max.text = "Min value : ${data.maxValue}"
                Glide.with(itemView).load(getImageUrl(iconId = data.imageId))
                    .into(binding.imgWeather)
            }
        }
    }


    private fun getImageUrl(iconId: Int) =
        "https://developer.accuweather.com/sites/default/files/${iconId}-s.png"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewAdaptor.ViewHolder {
        val binding =
            LayoutDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewAdaptor.ViewHolder, position: Int) {
        val currentCity = getItem(position)
        holder.onBind(currentCity)
    }

}