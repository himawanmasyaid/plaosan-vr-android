package com.hmwn.plaosanvr.view.splash

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    val progressState = MutableLiveData<Boolean>()

    init {
        start()
    }

    fun start() {
        viewModelScope.launch {
            delay(2000)
            progressState.postValue(true)
        }

    }

}