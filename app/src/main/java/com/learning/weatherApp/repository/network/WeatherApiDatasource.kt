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

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _weatherInfoListResponse = MutableLiveData<ArrayList<WeatherInfo>>()
    val weatherInfoListResponse: LiveData<ArrayList<WeatherInfo>>
        get() = _weatherInfoListResponse

    fun fetchWeatherInfo(location: List<Location>) {
        _weatherInfoListResponse.postValue(ArrayList())
        _networkState.postValue(NetworkState.LOADING)
        _isLoading.postValue(true)
        try {
            var singleList = mutableListOf<Single<WeatherInfo>>()
            location.forEach {
                singleList.add(
                    apiService.getWeatherDetails(it.latitude, it.longitude)
                        .subscribeOn(Schedulers.io())
                )
            }
            compositeDisposable.add(
                Single.mergeDelayError(singleList)
                    .doFinally { _isLoading.postValue(false) }
                    .subscribe({
                        Log.e("MovieDetailsDataSource", it.toString() ?: "")
                        _weatherInfoListResponse += it as WeatherInfo
                        _networkState.postValue(NetworkState.LOADED)
                    }, {
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDetailsDataSource", it.message ?: "")
                    })
            )
        } catch (e: Exception) {
            Log.e("MovieDetailsDataSource", e.message ?: "")
        }
    }

    operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: T) {
        val value = this.value ?: arrayListOf()
        value.add(values)
        this.postValue(value)
    }

}