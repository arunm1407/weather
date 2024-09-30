package com.example.weather.presentation

import androidx.lifecycle.ViewModel
import com.example.weather.data.Constants.TOKEN
import com.example.weather.data.enitity.SearchResult
import com.example.weather.domain.WeatherRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Arun @ak - 14213  on 30/09/24.
 */

@HiltAndroidApp
class ListViewModel @Inject constructor( private val repository: WeatherRepository) : ViewModel() {


    fun onSearchRecords(query: String): Flow<DataResult<SearchResult>> = flow {
        emit(DataResult.Loading)
        val result = kotlin.runCatching {
            repository.getWeatherList(
                mapOf(
                    "apikey" to TOKEN,
                    "q" to query
                )
            )
        }

        result.onSuccess { data ->
            if (data is DataResult.Success && data.data.list.isNotEmpty()) {
                emit(DataResult.Success(data.data))
            } else {
                emit(DataResult.Error(ErrorModel()))
            }
        }
            .onFailure {
                emit(DataResult.Error(ErrorModel()))
            }
    }


}

