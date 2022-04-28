package com.example.myapplication.room

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ActivityRoom : AppCompatActivity() {

    lateinit var database: ContactDatabase
    lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= ContactDatabase.getInstance(this)
        val database2=ContactDatabase.getInstance(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(1,"Mohit","0987654321", Date(),1))
        }


        try {
            database.contactDao().getContact().observe(this, Observer{
                Log.e("Contact","${it.toString()}")
            })
        }catch (e:Exception){
            Log.e("Exception","${e}")
        }



    }
}