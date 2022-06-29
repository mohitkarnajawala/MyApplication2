package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityHelloServiceBinding
import com.example.myapplication.service.HelloService
import android.content.ServiceConnection
import android.content.ComponentName
import android.content.Context

import android.os.IBinder
import com.example.myapplication.service.MyServiceDemo


class ActivityHelloService : AppCompatActivity() {

    companion object{
       val TAG= ActivityHelloService::class.java.simpleName
    }

    private val mStopLoop = false

    var count = 0

    private var isServiceBound = false

    private var serviceConnection: ServiceConnection? = null

    private lateinit var binding:ActivityHelloServiceBinding

    private var helloService:HelloService?=null

    private var serviceIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHelloServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e(TAG,"MainThread Id is : "+ Thread.currentThread().id)

        serviceIntent = Intent(this,MyServiceDemo::class.java)

        binding.btnStartService.setOnClickListener {

            startService(serviceIntent)
        }

        binding.btnStopService.setOnClickListener {

            stopService(serviceIntent)
        }

        binding.btnBindService.setOnClickListener {
            bindService()
        }

        binding.btnUnBindService.setOnClickListener {
            unbindService()
        }

        binding.btnRndmNumber.setOnClickListener {
            setRandomNumber()
        }

    }

    private fun bindService() {
        if (serviceConnection == null) {
            serviceConnection = object : ServiceConnection {
                override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                    val myServiceBinder: HelloService.MyServiceBinder = iBinder as HelloService.MyServiceBinder
                   // helloService = myServiceBinder.service
                    isServiceBound = true
                }

                override fun onServiceDisconnected(componentName: ComponentName) {
                    isServiceBound = false
                }
            }
        }
        bindService(serviceIntent, serviceConnection!!, Context.BIND_AUTO_CREATE)
    }

    private fun unbindService() {
        if (isServiceBound) {
            unbindService(serviceConnection!!)
            isServiceBound = false
        }
    }

    private fun setRandomNumber() {
        if (isServiceBound) {
            binding.textViewthreadCount.setText("Random number: " + helloService?.getRandomNumber())
        } else {
            binding.textViewthreadCount.setText("Service not bound")
        }
    }
}