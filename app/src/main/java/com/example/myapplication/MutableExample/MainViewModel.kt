package com.example.myapplication.MutableExample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
 *      MainViewModel
 *      - viewModel that updates the UI
 *      - gets the data from model
 */
class MainViewModel :ViewModel() {

    //create model object which contains data for our UI
    val uiTextUpdatedModel = DataModel(uiText = "Mohit is Android Developer")

    //create the mutableLiveData object to updates the Main UI
    val uiTextLiveData= MutableLiveData<String>()

    //create the getUpdatedData function to get the updated Value
    fun getUpdatedData(){

        val uiTextValue=uiTextUpdatedModel.uiText
        uiTextLiveData.postValue(uiTextValue)
    }


}