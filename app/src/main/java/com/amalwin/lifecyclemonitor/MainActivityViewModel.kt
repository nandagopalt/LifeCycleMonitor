package com.amalwin.lifecyclemonitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class MainActivityViewModel constructor() : ViewModel() {
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    init {
        println("MainActivity ViewModel init block called...")
        _counter.value = 1
    }

    suspend fun incrementCount() {
        for(i in 1..10) {
            _counter.postValue(_counter.value?.plus(1))
            delay(1000L)
        }
    }
}