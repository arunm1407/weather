package com.example.weather.domain

import com.example.weather.data.enitity.SearchResult
import com.example.weather.data.enitity.WeatherMetadata
import com.example.weather.presentation.DataResult

/**
 * Created by Arun @ak - 14213  on 30/09/24.
 */
interface WeatherRepository {
    suspend fun getWeatherList(param : Map<String,String>) : DataResult<SearchResult>
    suspend fun getDetailWeatherReport(param : Map<String,String>) : DataResult<WeatherMetadata>
}