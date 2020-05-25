package com.learning.weatherApp.repository.vo

import android.os.Parcel
import android.os.Parcelable


data class Currently(

    val apparentTemperature: Double,

    val cloudCover: Double,

    val dewPoint: Double,

    val humidity: Double,

    val icon: String,

    val nearestStormBearing: Double,

    val nearestStormDistance: Double,

    val ozone: Double,

    val precipIntensity: Double,

    val precipProbability: Double,

    val pressure: Double,

    val summary: String,

    val temperature: Double,

    val time: Long,

    val uvIndex: Double,

    val visibility: Double,

    val windBearing: Double,

    val windGust: Double,

    val windSpeed: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()?:"",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()?:"",
        parcel.readDouble(),
        parcel.readLong(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(apparentTemperature)
        parcel.writeDouble(cloudCover)
        parcel.writeDouble(dewPoint)
        parcel.writeDouble(humidity)
        parcel.writeString(icon)
        parcel.writeDouble(nearestStormBearing)
        parcel.writeDouble(nearestStormDistance)
        parcel.writeDouble(ozone)
        parcel.writeDouble(precipIntensity)
        parcel.writeDouble(precipProbability)
        parcel.writeDouble(pressure)
        parcel.writeString(summary)
        parcel.writeDouble(temperature)
        parcel.writeLong(time)
        parcel.writeDouble(uvIndex)
        parcel.writeDouble(visibility)
        parcel.writeDouble(windBearing)
        parcel.writeDouble(windGust)
        parcel.writeDouble(windSpeed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currently> {
        override fun createFromParcel(parcel: Parcel): Currently {
            return Currently(parcel)
        }

        override fun newArray(size: Int): Array<Currently?> {
            return arrayOfNulls(size)
        }
    }
}