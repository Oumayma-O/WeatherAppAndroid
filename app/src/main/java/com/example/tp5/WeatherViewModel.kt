package com.example.tp5

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel : ViewModel() {

    private val _temperature = MutableLiveData<String>()
    val temperature: LiveData<String> get() = _temperature

    private val _humidity = MutableLiveData<String>()
    val humidity: LiveData<String> get() = _humidity

    private val _pressure = MutableLiveData<String>()
    val pressure: LiveData<String> get() = _pressure

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _country = MutableLiveData<String>()
    val country: LiveData<String> get() = _country

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> get() = _location

    private val _iconUrl = MutableLiveData<String>()
    val iconUrl: LiveData<String> get() = _iconUrl


    fun fetchWeather(cityName: String) {
        viewModelScope.launch {
            try {
                // Use enqueue instead of execute
                RetrofitHelper.retrofitService.getWeather(cityName, "17db59488cadcad345211c36304a9266")
                    .enqueue(object : Callback<Weather> {
                        override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                            if (response.isSuccessful) {
                                val weatherData = response.body()

                                _temperature.value = "${weatherData?.main?.temp} °C"
                                _humidity.value = "${weatherData?.main?.humidity}%"
                                _pressure.value = "${weatherData?.main?.pressure} hPa"
                                _description.value = weatherData?.weather?.firstOrNull()?.description
                                _country.value = weatherData?.sys?.country ?: "N/A"
                                _location.value = weatherData?.name ?: "N/A"
                                _iconUrl.value = weatherData?.weather?.firstOrNull()?.icon ?: "01d"

                            } else {
                                // Gérer l'échec de la requête ici
                                println("API request failed with code: ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<Weather>, t: Throwable) {
                            // Gérer les erreurs réseau ici
                            println("Network request failed with exception: $t")
                        }
                    })
            } catch (e: IOException) {
                // Gérer les erreurs réseau ici
                println("IOException occurred: $e")
            } catch (e: JsonSyntaxException) {
                // Handle JSON parsing errors
                println("JSON parsing error: $e")
            } catch (e: NumberFormatException) {
                // Handle NumberFormatException
                println("NumberFormatException occurred: $e")
            }
        }
    }



}
