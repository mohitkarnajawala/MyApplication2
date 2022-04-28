package com.example.myapplication.coroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActivityCoroutineModel : ViewModel() {

    private var TAG =  ActivityCoroutineModel::class.java.simpleName

    init {
        viewModelScope.launch {
            while (true)
                delay(500)
           funtion()
        }
    }

    private fun funtion() {
        Log.e(TAG," Coroutine Practice")
    }

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG,"VIew Model Destroyed")
    }
}