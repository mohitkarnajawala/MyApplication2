package com.example.myapplication.Quotes

import android.graphics.pdf.PdfDocument
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class QuotesRepository(val quotesApi: QuotesApi) {

   private val quoteLiveData = MutableLiveData<QuoteList>()


    suspend fun getQuotes(page: Int){
       val result= quotesApi.getQuotes(page)
       if(result.body() !=null){

           quoteLiveData.postValue(result.body())
       }
   }
    val quotes: LiveData<QuoteList>
        get() = quoteLiveData

}