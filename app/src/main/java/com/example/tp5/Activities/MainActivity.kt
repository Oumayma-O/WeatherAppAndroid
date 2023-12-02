package com.example.tp5.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.tp5.R
import com.example.tp5.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var citySpinner: Spinner
    private lateinit var forecastButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        citySpinner = findViewById(R.id.citySpinner)

        val cities = arrayOf("Germany","Tunis", "Paris", "New York", "Tokyo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        citySpinner.adapter = adapter

        citySpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedCity = cities[position]
                weatherViewModel.fetchWeather(selectedCity)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        })

        forecastButton = findViewById(R.id.forecast)

        forecastButton.setOnClickListener {
            val selectedCity = citySpinner.selectedItem.toString()

            val intent = Intent(this, ForecastActivity::class.java)
            intent.putExtra("selectedLocation", selectedCity)
            startActivity(intent)
        }

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

        weatherViewModel.location.observe(this) { location ->
            findViewById<TextView>(R.id.location).text = location
        }

        weatherViewModel.country.observe(this) { country ->
            findViewById<TextView>(R.id.country).text = country
        }

       /* weatherViewModel.iconUrl.observe(this) { iconUrl ->
            val imageView = findViewById<ImageView>(R.id.imageView2)

            Picasso.get().load(iconUrl).into(imageView)
        }*/

        // Appeler la méthode pour récupérer les données
        weatherViewModel.fetchWeather("Tokyo")
    }
}