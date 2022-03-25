package com.example.myapplication.Quotes

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {

    @GET("/quotes")
   suspend fun getQuotes(@Query("page")page: Int) :Response<QuoteList>
}