package com.example.myapplication.Quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuotesViewModel(private val quotesRepository: QuotesRepository):ViewModel() {

    init {
       viewModelScope.launch {
           quotesRepository.getQuotes(1)
       }
    }

    //When their is change into the quotes live data into repository class
    //also it will do change into this quote live data
    val quote:LiveData<QuoteList>
    get() = quotesRepository.quotes
}