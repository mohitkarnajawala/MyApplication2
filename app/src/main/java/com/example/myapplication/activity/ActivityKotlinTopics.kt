package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.coroutine.ActivityCoroutine
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.reactivex.ActivityReactivEX
import com.example.myapplication.room.ActivityRoom

class ActivityKotlinTopics : AppCompatActivity() {


    private lateinit var activityKotlinTopics :ActivityKotlinTopics
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityKotlinTopics=this

        binding.btnService.setOnClickListener {
           // startActivity(Intent(this, LocationService::class.java))
            startActivity(Intent(this, ActivityHelloService::class.java))
        }

        binding.btnEspresso.setOnClickListener {
            startActivity(Intent(this, ActivityEspressoTestLanguage::class.java))
        }

        binding.btnBiometric.setOnClickListener {
            startActivity(Intent(this, ActivityBiometric::class.java))
        }

        binding.btnJobIntentService.setOnClickListener {
            startActivity(Intent(this, ActivityJobIntentService::class.java))
        }

        binding.btnCoroutine.setOnClickListener {
            startActivity(Intent(this, ActivityCoroutine::class.java))
        }

        binding.btnReactiveX.setOnClickListener {
            startActivity(Intent(this, ActivityReactivEX::class.java))
        }

        binding.btnRoom.setOnClickListener {
            startActivity(Intent(this,ActivityRoom::class.java))
        }

    }
}