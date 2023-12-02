package com.example.tp5.forecastModels

import com.example.tp5.CoordX

data class City(
    val coord: CoordX,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)