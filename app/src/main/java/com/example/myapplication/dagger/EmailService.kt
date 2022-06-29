package com.example.myapplication.dagger

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


interface NotificationService{
    fun send(to:String,from: String, body: String?)
}
@ActivityScope
class EmailService @Inject constructor():NotificationService {

    override fun send(to:String, from: String, body: String?){
        Log.e("Dagger:-","Send via email")
    }

}

class MessageService(private val retryCount:Int) :NotificationService{
    override fun send(to: String, from: String, body: String?) {
        Log.e("Dagger:-","Send via message- retry count ${retryCount}")
    }

}