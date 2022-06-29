package com.example.myapplication.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.myapplication.databinding.ActivityBiometricBinding
import com.example.myapplication.receiver.MyReciever
import com.example.myapplication.worker.MyWorker
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ActivityBiometric:AppCompatActivity() {

    private lateinit var binding:ActivityBiometricBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    lateinit var  myConstraints:Constraints


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

       // add specific constraints in our WorkRequest to customize
         myConstraints = Constraints.Builder()
           //.setRequiresDeviceIdle(true) //checks whether device should be idle for the WorkRequest to run
            .setRequiresCharging(true) //checks whether device should be charging for the WorkRequest to run
            .setRequiredNetworkType(NetworkType.CONNECTED) //checks whether device should have Network Connection
            //.setRequiresBatteryNotLow(true) // checks whether device battery should have a specific level to run the work request
            //.setRequiresStorageNotLow(true) // checks whether device storage should have a specific level to run the work request
            .build()

        binding.btnBiometric.setOnClickListener {
          //  biometricPrompt.authenticate(promptInfo)
            setUpWorkRequest()

        }

    }

    fun setUpWorkRequest(){

        val workRequest= PeriodicWorkRequest.Builder(MyWorker::class.java,1,TimeUnit.MINUTES)
            .setConstraints(myConstraints)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)

    }

    override fun onStart() {
        super.onStart()
        val intent= IntentFilter()
        intent.addAction("android.intent.action.AIRPLANE_MODE")
        intent.addAction("android.intent.action.ACTION_POWER_CONNECTED")
        val reciever = MyReciever()
        registerReceiver(reciever,intent)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(MyReciever())
    }


}