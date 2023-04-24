package com.example.kata.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kata.R
import com.example.kata.Weather

class WeatherAdapter(private val context: Context, private val weatherList: MutableList<Weather>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvTemp: TextView = itemView.findViewById(R.id.tv_temp)
        val tvIcon: ImageView = itemView.findViewById(R.id.tv_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weatherList[position]
        holder.tvName.text = item.name
        holder.tvTemp.text = item.main.temp.toString() + "°C"
        Glide.with(context).load("https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png").into(holder.tvIcon);
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}