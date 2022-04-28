package com.example.myapplication.Helper

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class ConnectionNetworkState constructor(private var context: Context) :
    LiveData<NetworkAvailability>() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var instance: ConnectionNetworkState

        /*
        * @Here is making singleton class
        * */
        fun get(context: Context):ConnectionNetworkState{

            instance = if (::instance.isInitialized){
                instance
            }else{
                ConnectionNetworkState(context)
            }
            return instance

        }


    }

    private val connectivityBroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(p0: Context?, p1: Intent?) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo

            value = if (netInfo != null && netInfo.isConnected) {
                NetworkAvailability.CONNECTED
            } else {
                NetworkAvailability.DISCONNECTED
            }
        }
    }


    override fun onActive(){
        super.onActive()
        value = NetworkAvailability.UNKNOWN
        val broadcastIntent = IntentFilter()
        broadcastIntent.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(connectivityBroadcastReceiver, broadcastIntent)
    }


    override fun onInactive(){
        super.onInactive()
        context.unregisterReceiver(connectivityBroadcastReceiver)
    }



}