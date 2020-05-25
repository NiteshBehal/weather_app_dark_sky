package com.learning.weatherApp.repository.network.api

import com.learning.weatherApp.repository.vo.WeatherInfo
import io.reactivex.ObservableSource
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiInterface {
    @GET("forecast/900435862f097f4cc7a2021dd67b8c12/{lat},{long}")
    fun getWeatherDetails(@Path("lat") lat: Double, @Path("long") id: Double): Single<WeatherInfo>

//    @GET("forecast/900435862f097f4cc7a2021dd67b8c12/{lat},{long}")
//    fun getWeatherDetail(@Path("lat") lat: Double, @Path("long") id: Double): ObservableSource<WeatherInfo>

}