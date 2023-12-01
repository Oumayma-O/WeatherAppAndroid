package com.example.tp5

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel : ViewModel() {

    // Déclarez des MutableLiveData pour les différentes données
    private val _temperature = MutableLiveData<String>()
    val temperature: LiveData<String> get() = _temperature

    private val _humidity = MutableLiveData<String>()
    val humidity: LiveData<String> get() = _humidity

    private val _pressure = MutableLiveData<String>()
    val pressure: LiveData<String> get() = _pressure

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    // ...

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                // Use enqueue instead of execute
                RetrofitHelper.retrofitService.getWeather().enqueue(object : Callback<Weather> {
                    override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                        if (response.isSuccessful) {
                            val weatherData = response.body()

                            // Mettez à jour les MutableLiveData avec les données
                            _temperature.value = "${weatherData?.main?.temp} °C"
                            _humidity.value = "${weatherData?.main?.humidity}%"
                            _pressure.value = "${weatherData?.main?.pressure} hPa"
                            _description.value = weatherData?.weather?.firstOrNull()?.description
                        } else {
                            // Gérer l'échec de la requête ici
                        }
                    }

                    override fun onFailure(call: Call<Weather>, t: Throwable) {
                        // Gérer les erreurs réseau ici
                    }
                })
            } catch (e: IOException) {
                // Gérer les erreurs réseau ici
            }
        }
    }

}
