package com.learning.weatherApp.repository.vo

import android.os.Parcel
import android.os.Parcelable


data class WeatherInfo(
    val currently: Currently,

    val latitude: Double,

    val longitude: Double,

    val offset: Int,

    val timezone: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Currently::class.java.classLoader)!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(currently, flags)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeInt(offset)
        parcel.writeString(timezone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherInfo> {
        override fun createFromParcel(parcel: Parcel): WeatherInfo {
            return WeatherInfo(parcel)
        }

        override fun newArray(size: Int): Array<WeatherInfo?> {
            return arrayOfNulls(size)
        }
    }
}