package com.example.kata

import com.google.gson.annotations.SerializedName
import java.time.temporal.Temporal

data class Weather(
    val name: String,
    val weather: Array<WeatherData>,
    val main: MainData,
)

data class WeatherData(
    val description: String
)

data class MainData(
    val temp: Float
)

