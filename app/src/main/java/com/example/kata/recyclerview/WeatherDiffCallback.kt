package com.example.kata.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.kata.Weather

class WeatherDiffCallback : DiffUtil.ItemCallback<Weather>() {

    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.equals(newItem)
    }
}
