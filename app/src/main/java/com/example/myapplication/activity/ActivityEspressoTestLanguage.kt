package com.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityEspressoTestLanguageBinding

class ActivityEspressoTestLanguage : AppCompatActivity() {

    private lateinit var binding:ActivityEspressoTestLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEspressoTestLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.english.setOnClickListener {
            binding.preferredLanguage.text="English"
        }
        binding.german.setOnClickListener {
            binding.preferredLanguage.text="German"
        }
        binding.french.setOnClickListener {
            binding.preferredLanguage.text="French"
        }
        binding.hindi.setOnClickListener {
            binding.preferredLanguage.text="Hindi"
        }
        binding.urdu.setOnClickListener {
            binding.preferredLanguage.text="Urdu"
        }
    }
}