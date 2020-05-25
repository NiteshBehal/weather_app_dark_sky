package com.learning.weatherApp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.weatherApp.repository.network.api.NetworkState

class AppActivityViewModel  : ViewModel() {

    val updateListData  = MutableLiveData<Boolean>()

    fun getUpdateFlag(): LiveData<Boolean>{
        return updateListData
    }

    fun setUpdateFlag(doUpdate: Boolean){
        updateListData.postValue(doUpdate)
    }
}