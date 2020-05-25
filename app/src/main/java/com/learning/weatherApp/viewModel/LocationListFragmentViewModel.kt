package com.learning.weatherApp.viewModel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.weatherApp.repository.WeatherAppRepository
import com.learning.weatherApp.repository.network.api.NetworkState
import com.learning.weatherApp.repository.vo.WeatherInfo
import io.reactivex.disposables.CompositeDisposable

class LocationListFragmentViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val weatherAppRepository by lazy {
        WeatherAppRepository(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        weatherAppRepository.fetchWeatherDetailsNetworkState()
    }

    val weatherInfoList : LiveData<List<WeatherInfo>> by lazy {
        weatherAppRepository.fetchWeatherInfoList()
    }

    fun fetchWeatherInfo() {
        weatherAppRepository.fetchWeatherInfo(getLocationList())
    }

    fun getLocationList(): List<Location> {
        var loc1 = Location("default_provider")
        loc1.latitude = 37.3855;
        loc1.longitude = -122.088

        var loc2 = Location("default_provider")
        loc2.latitude = 40.7128;
        loc2.longitude = -74.0060

        return listOf(loc1, loc2)
    }

    fun listIsEmpty(): Boolean {
        return weatherInfoList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}