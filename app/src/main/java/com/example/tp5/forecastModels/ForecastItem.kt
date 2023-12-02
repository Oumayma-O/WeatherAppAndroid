package com.example.tp5.forecastModels

import android.os.Parcel
import android.os.Parcelable

data class ForecastItem(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp,
    val feels_like: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val weather: List<WeatherXX>,
    val speed: Double,
    val deg: Int,
    val gust: Double,
    val clouds: Int,
    val pop: Double,
    val rain: Double? // Optional, as it might be absent in some responses
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readParcelable(Temp::class.java.classLoader)!!,
        parcel.readParcelable(FeelsLike::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(WeatherXX.CREATOR)!!,
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readValue(Double::class.java.classLoader) as? Double
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(dt)
        parcel.writeLong(sunrise)
        parcel.writeLong(sunset)
        parcel.writeParcelable(temp, flags)
        parcel.writeParcelable(feels_like, flags)
        parcel.writeInt(pressure)
        parcel.writeInt(humidity)
        parcel.writeTypedList(weather)
        parcel.writeDouble(speed)
        parcel.writeInt(deg)
        parcel.writeDouble(gust)
        parcel.writeInt(clouds)
        parcel.writeDouble(pop)
        parcel.writeValue(rain)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ForecastItem> {
        override fun createFromParcel(parcel: Parcel): ForecastItem {
            return ForecastItem(parcel)
        }

        override fun newArray(size: Int): Array<ForecastItem?> {
            return arrayOfNulls(size)
        }
    }
}
