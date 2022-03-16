package com.example.myapplication.recyclerview

class MainRepository constructor(private val retrofitService:RetrofitService) {
    fun getAllMovies() = retrofitService.getAllMovies()
}