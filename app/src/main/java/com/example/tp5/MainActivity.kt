package com.example.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        // Observer les LiveData et mettre à jour l'interface utilisateur
        weatherViewModel.temperature.observe(this) { temperature ->
            findViewById<TextView>(R.id.temperature).text = temperature
        }

        weatherViewModel.humidity.observe(this) { humidity ->
            findViewById<TextView>(R.id.humidity).text = humidity
        }

        weatherViewModel.pressure.observe(this) { pressure ->
            findViewById<TextView>(R.id.pressure).text = pressure
        }

        weatherViewModel.description.observe(this) { description ->
            findViewById<TextView>(R.id.description).text = description
        }

        // Appeler la méthode pour récupérer les données
        weatherViewModel.fetchWeather()
    }
}