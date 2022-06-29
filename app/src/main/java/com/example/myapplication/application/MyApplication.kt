package com.example.myapplication.application

import android.app.Application
import com.example.myapplication.dagger.AppComponent
import com.example.myapplication.dagger.DaggerAppComponent
import com.example.myapplication.dagger.DaggerUserRegistrationComponent
import com.example.myapplication.dagger.UserRegistrationComponent
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class MyApplication : Application() {

   // lateinit var userRegistrationComponent: UserRegistrationComponent
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

       // userRegistrationComponent= DaggerUserRegistrationComponent.factory().Create(4)
        appComponent= DaggerAppComponent.builder().build()
    }
}
