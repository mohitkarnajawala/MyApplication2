package com.example.myapplication.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AnalyticsModule::class])
interface AppComponent {

    fun getAnalyticsService(): AnalyticsService
}