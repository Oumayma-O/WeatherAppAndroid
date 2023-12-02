package com.example.tp5.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.WeatherAdapter
import com.example.tp5.R
import com.gl4.tp5mobile.ForecastViewModel

class ForecastActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cityNameTextView: TextView
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var backArrow: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        cityNameTextView = findViewById(R.id.cityName)
        backArrow = findViewById(R.id.backArrow)


        val city = intent.getStringExtra("selectedLocation")

        if (city != null) {
            forecastViewModel.getForecast(city)
        }



        forecastViewModel.forecast.observe(this, Observer { forecast ->
            if (forecast != null) {
                recyclerView.adapter = WeatherAdapter(forecast)
                cityNameTextView.text = forecast.city.name
            }
        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ForecastActivity)
            adapter = WeatherAdapter(forecastViewModel.forecast.value)
        }

        backArrow.setOnClickListener {
            val intent = Intent(this@ForecastActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
