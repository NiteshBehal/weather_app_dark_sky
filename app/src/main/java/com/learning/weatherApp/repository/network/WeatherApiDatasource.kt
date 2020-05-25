package com.learning.weatherApp.repository.network.api

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learning.weatherApp.repository.vo.WeatherInfo
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class WeatherApiDatasource(
    private val apiService: WeatherApiInterface,
    private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _weatherInfoListResponse = MutableLiveData<List<WeatherInfo>>()
    val weatherInfoListResponse: LiveData<List<WeatherInfo>>
        get() = _weatherInfoListResponse

    fun fetchWeatherInfo(location: List<Location>) {
        _weatherInfoListResponse.value = listOf()
        _networkState.postValue(NetworkState.LOADING)
        try {
            var singleList = mutableListOf<Single<WeatherInfo>>()
            location.forEach {
                singleList.add(
                    apiService.getWeatherDetails(it.latitude, it.longitude)
                        .subscribeOn(Schedulers.io())
                )
            }

            compositeDisposable.add(Single.zip(singleList) { args -> args }
                .subscribe({
                    Log.e("MovieDetailsDataSource", it.asList().toString() ?: "")
                    _weatherInfoListResponse.postValue((it.asList() as List<WeatherInfo>))
                    _networkState.postValue(NetworkState.LOADED)
                }, {
                    _networkState.postValue(NetworkState.ERROR)
                    Log.e("MovieDetailsDataSource", it.message ?: "")
                }))

        } catch (e: Exception) {
            Log.e("MovieDetailsDataSource", e.message ?: "")
        }
    }


}