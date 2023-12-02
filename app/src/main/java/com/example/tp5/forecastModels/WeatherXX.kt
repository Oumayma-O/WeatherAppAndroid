package com.example.tp5.forecastModels

import android.os.Parcel
import android.os.Parcelable

data class WeatherXX(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(icon)
        parcel.writeInt(id)
        parcel.writeString(main)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherXX> {
        override fun createFromParcel(parcel: Parcel): WeatherXX {
            return WeatherXX(parcel)
        }

        override fun newArray(size: Int): Array<WeatherXX?> {
            return arrayOfNulls(size)
        }
    }
}