package com.example.tp5

import com.example.tp5.forecastModels.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    fun getWeather(@Query("q") cityName: String, @Query("APPID") apiKey: String): Call<Weather>
    @GET("forecast/daily?APPID=17db59488cadcad345211c36304a9266&units=metric")
    fun getForecast(@Query("q") city : String): Call<Forecast>

}
