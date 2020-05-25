package com.learning.weatherApp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppActivityViewModel  : ViewModel() {

    val updateListData  = MutableLiveData<Boolean>(false)

    fun getUpdateFlag(): LiveData<Boolean>{
        return updateListData
    }

    fun setUpdateFlag(doUpdate: Boolean){
        updateListData.postValue(doUpdate)
    }
}