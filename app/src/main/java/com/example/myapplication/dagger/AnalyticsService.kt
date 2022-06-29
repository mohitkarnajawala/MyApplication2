package com.example.myapplication.dagger

import android.util.Log

interface AnalyticsService {

    fun trackEvent(eventName:String,eventType:String)
}

class Mixpanel: AnalyticsService{

    override fun trackEvent(eventName: String, eventType: String) {
        Log.e("Dagger:-","Track Event on Mixpanel")
    }

}

class FirebaseAnalytics:AnalyticsService{
    override fun trackEvent(eventName: String, eventType: String) {
        Log.e("Dagger:-","Track Event on Firebase Analytics")
    }

}