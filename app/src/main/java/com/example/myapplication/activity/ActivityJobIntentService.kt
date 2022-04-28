package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.JobIntentService.enqueueWork

import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityJobIntentServiceBinding
import com.example.myapplication.service.MyJobIntentService




class ActivityJobIntentService : AppCompatActivity(){

    private lateinit var binding:ActivityJobIntentServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityJobIntentServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonJobIntent.setOnClickListener {
            onStartJobIntentService()
        }


    }

    private fun onStartJobIntentService() {
        val mIntent = Intent(this, MyJobIntentService::class.java)
        mIntent.putExtra("maxCountValue", 1000)
        //MyJobIntentService.enqueueWork(this, mIntent)
    }


}