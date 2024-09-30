package com.example.weather.data.api

import retrofit2.Response
import retrofit2.http.QueryMap

/**
 * Created by Arun @ak - 14213  on 30/09/24.
 */
interface ApiService {
    suspend fun searchWeather(@QueryMap paramMap : Map<String,String>) : Response
}