package com.example.youtube_api.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel :ViewModel(){

    val loading =MutableLiveData<Boolean>()
}