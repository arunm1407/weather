package com.example.weather.presentation.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.enitity.WeatherCity
import com.example.weather.databinding.LayoutListItemBinding

/**
 * Created by Arun @ak - 14213  on 30/09/24.
 */


class ListViewAdaptor(val onCitySelected: (city: WeatherCity) -> Unit) :
    ListAdapter<WeatherCity, ListViewAdaptor.ViewHolder>(DIFF_UTIL) {


    object DIFF_UTIL : DiffUtil.ItemCallback<WeatherCity>() {
        override fun areItemsTheSame(oldItem: WeatherCity, newItem: WeatherCity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WeatherCity, newItem: WeatherCity): Boolean {
            return oldItem == newItem
        }
    }


    inner class ViewHolder(private val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: WeatherCity) {
            binding.apply {

                root.setOnClickListener {
                    onCitySelected(data)
                }
                city.text = data.name
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCity = getItem(position)
        holder.onBind(currentCity)
    }
}