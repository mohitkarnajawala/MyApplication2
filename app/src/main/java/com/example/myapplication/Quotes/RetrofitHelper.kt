package com.example.myapplication.Quotes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object{
        var BASE_URL: String ="https://quotable.io"
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }


}