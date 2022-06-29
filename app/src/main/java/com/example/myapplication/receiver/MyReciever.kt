package com.example.myapplication.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReciever : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.e("MyReceiver:",""+p1?.action)
        Toast.makeText(p0, p1?.action.toString(),
            Toast.LENGTH_LONG).show()
    }
}