package com.example.kata


data class Weather(
    val name: String,
    val weather: Array<WeatherData>,
    val main: MainData,
)

data class WeatherData(
    val icon: String,
)

data class MainData(
    val temp: Float
)


