package com.example.weather.presentation

sealed class DataResult<out T> {
    data class Success<out R>(val data: R) : DataResult<R>()
    data class Error(val error: ErrorModel) : DataResult<Nothing>()
     data object Loading : DataResult<Nothing>()

    fun handle(
        onSuccess: (T) -> Unit,
        onError: (ErrorModel) -> Unit,
        onLoading: () -> Unit = {}
    ) {
        when (this) {
            is Success -> onSuccess(data)
            is Error -> onError(error)
            is Loading -> onLoading()
        }
    }

}

data class ErrorModel(
    val errorBody: String? = null,
    val errorMessage: String? = null,
    val exception: Exception? = null
)