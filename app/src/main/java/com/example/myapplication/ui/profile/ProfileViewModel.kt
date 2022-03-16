package com.example.myapplication.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    val _text= MutableLiveData<String>().apply {
        value="This is Profile Page"
    }
    val text : LiveData<String> =_text
}