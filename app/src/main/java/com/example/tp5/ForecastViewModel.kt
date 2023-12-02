package com.gl4.tp5mobile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp5.App
import com.example.tp5.RetrofitHelper
import com.example.tp5.forecastModels.Forecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel(private val context: Context) : ViewModel() {
    private val _forecast = MutableLiveData<Forecast>()
    val forecast: LiveData<Forecast> get() = _forecast

    constructor() : this(App.instance)

    fun getForecast(city: String) {
        RetrofitHelper.retrofitService.getForecast(city).enqueue(
            object : Callback<Forecast> {
                override fun onResponse(
                    call: Call<Forecast>,
                    response: Response<Forecast>
                ) {
                    if (response.isSuccessful) {
                        _forecast.value = response.body()
                        _forecast.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Forecast>, t: Throwable) {
                    // Handle failure if needed
                }
            }
        )
    }
}
