package com.example.kata.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kata.R
import com.example.kata.Weather

class WeatherAdapter(private val weatherList: MutableList<Weather>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        //    val tvTemp: TextView = itemView.findViewById(R.id.tv_temp)
        //   val tvIcon : TextView = itemView.findViewById(R.id.tv_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weatherList[position]
        holder.tvName.text = item.name
        //holder.tvTemp.text = String().format(item.main.temp)
        // holder.tvIcon.text = item.weather.icon
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }


}