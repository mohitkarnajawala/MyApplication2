package com.example.myapplication.dagger

import android.util.Log
import javax.inject.Inject

interface UserRepository{
    fun saveUser(email:String, password:String)
}
@ActivityScope
class SqlRepository @Inject constructor(val analyticsService: AnalyticsService):UserRepository {

    override fun saveUser(email:String, password:String){
        Log.e("Dagger:-","User Saved to Database")
        analyticsService.trackEvent("Save the user","Create")
    }
}

class FirebaseRepository(val analyticsService: AnalyticsService) : UserRepository{
    override fun saveUser(email: String, password: String) {
        Log.e("Dagger:-","User Saved to Firebase")
        analyticsService.trackEvent("Save the user","Create")
    }

}