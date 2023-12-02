package com.example.tp5.forecastModels

import android.os.Parcel
import android.os.Parcelable

data class FeelsLike(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(day)
        parcel.writeDouble(eve)
        parcel.writeDouble(morn)
        parcel.writeDouble(night)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeelsLike> {
        override fun createFromParcel(parcel: Parcel): FeelsLike {
            return FeelsLike(parcel)
        }

        override fun newArray(size: Int): Array<FeelsLike?> {
            return arrayOfNulls(size)
        }
    }
}
