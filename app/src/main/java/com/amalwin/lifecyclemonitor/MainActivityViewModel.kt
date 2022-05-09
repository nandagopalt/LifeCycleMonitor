package com.amalwin.lifecyclemonitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel constructor() : ViewModel() {
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    private val _countStateFlow = MutableStateFlow<Int>(1)
    val countStateFlow: StateFlow<Int>
        get() = _countStateFlow

    init {
        println("MainActivity ViewModel init block called...")
        _counter.value = 1
    }

    suspend fun incrementCount() {
        for (i in 1..100) {
            _counter.postValue(_counter.value?.plus(1))
            delay(1000L)
        }
    }

    suspend fun incrementFlowCount() {
        for(i in 1..100) {
           _countStateFlow.value = _countStateFlow.value.plus(1)
            delay(1000L)
        }
    }
}

sealed class CounterUIState {
    object Empty : CounterUIState()
    data class Increment(val message: String?) : CounterUIState()
    data class Error(val errorMessage: String?) : CounterUIState()
}