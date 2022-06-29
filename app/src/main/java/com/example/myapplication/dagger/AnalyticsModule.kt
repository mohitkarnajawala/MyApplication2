package com.example.myapplication.dagger

import dagger.Module
import dagger.Provides
//import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
class AnalyticsModule {

    @Singleton
    @Provides
    fun getAnalyticsService() : AnalyticsService{
     return Mixpanel()
 }
}