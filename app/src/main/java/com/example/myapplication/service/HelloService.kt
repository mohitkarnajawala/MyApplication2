package com.example.myapplication.service

import android.R
import android.app.Service
import android.content.Intent
import android.os.*
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.util.Log

import android.widget.Toast
import java.lang.Process
import android.os.Binder
import com.example.myapplication.service.HelloService.MyServiceBinder

import android.os.IBinder
import java.util.*
import com.example.myapplication.service.HelloService.RandomNumberRequestHandler

import android.os.Messenger
import androidx.annotation.Nullable


class HelloService : Service() {
    private var mRandomNumber = 0
    private var mIsRandomGeneratorOn = false

    private val MIN = 0
    private val MAX = 100

    var GET_COUNT = 0


    inner class RandomNumberRequestHandler : Handler() {
        override fun handleMessage(msg: Message) {
            Log.e("HelloService: ", "Message intercepted")
            when (msg.what) {
                GET_COUNT -> {
                    val messageSendRandomNumber: Message = Message.obtain(null, GET_COUNT)
                    messageSendRandomNumber.arg1 = getRandomNumber()
                    try {
                        Log.e(
                            "HelloService: ",
                            "Replaying with random number to requester"
                        )
                        msg.replyTo.send(messageSendRandomNumber)
                    } catch (e: RemoteException) {
                        Log.e("HelloService: ", "" + e.message)
                    }
                }
            }
            super.handleMessage(msg)
        }
    }

    override fun onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        Log.e("HelloService:","OnCreate")

    }



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()
        Log.e("HelloService:","OnStartCommand Thread id is: "+ Thread.currentThread().id)
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job


        mIsRandomGeneratorOn = true
        Thread {
            startRandomNumberGenerator()
        }.start()
        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    internal class MyServiceBinder : Binder() {
        val service: MyServiceBinder
            get() = this
    }

    private val mBinder: IBinder = MyServiceBinder()


    private val randomNumberMessenger = Messenger(RandomNumberRequestHandler())

    override fun onBind(intent: Intent): IBinder? {
        Log.e("HelloService:", "In OnBind")
        return if (intent.getPackage() === "serviceclientapp.youtube.com.messengerserviceclientapp") {
            randomNumberMessenger.binder
        } else {
            mBinder
        }
    }

    override fun onRebind(intent: Intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
        Log.e("HelloService:","OnRebind the Service"+ Thread.currentThread().id)
        Toast.makeText(this, "service re-binding", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        Log.e("HelloService:","OnDestroy")
        stopRandomNumberGenerator()
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("HelloService:", "In onUnbind")
        return super.onUnbind(intent)
    }

    fun getRandomNumber(): Int {
        return mRandomNumber
    }

    private fun startRandomNumberGenerator() {
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(1000)
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = Random().nextInt(MAX) + MIN
                    Log.e(
                        "HelloService:",
                        "Thread id: " + Thread.currentThread().id + ", Random Number: " + mRandomNumber
                    )
                }
            } catch (e: InterruptedException) {
                Log.e("HelloService:", "Thread Interrupted")
            }
        }
    }

    private fun stopRandomNumberGenerator() {
        mIsRandomGeneratorOn = false
    }
}
