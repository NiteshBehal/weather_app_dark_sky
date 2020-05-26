package com.learning.weatherApp.repository.network.api

import com.learning.weatherApp.repository.vo.WeatherInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiInterface {
    //    @GET("forecast/900435862f097f4cc7a2021dd67b8c12/{lat},{long}")
    @GET("forecast/360df1e319e95044fe7efd7c859c85b8/{lat},{long}")
    fun getWeatherDetails(@Path("lat") lat: Double, @Path("long") id: Double): Single<WeatherInfo>
}