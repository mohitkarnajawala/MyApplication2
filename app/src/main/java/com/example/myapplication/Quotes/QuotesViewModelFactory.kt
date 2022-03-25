package com.example.myapplication.Quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuotesViewModelFactory(private val quotesRepository: QuotesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return  QuotesViewModel(quotesRepository) as T
    }
}