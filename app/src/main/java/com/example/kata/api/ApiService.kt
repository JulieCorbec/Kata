package com.example.kata.api

import com.example.kata.Weather
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("weather")
    suspend fun getWeather(@Query("name") name : String, appId : String
    ): Response<MutableList<Weather>>


}