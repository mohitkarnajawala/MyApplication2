package com.example.myapplication.LiveDataExample

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityLiveDataMainBinding
import java.util.EnumSet.of
import java.util.List.of


class LiveDataMain : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding class object that is used to access the layout's variables and views
        binding = ActivityLiveDataMainBinding.inflate(layoutInflater)

        //set the layout using binding object
        setContentView(binding.root)

        Log.e(TAG, "onCreate")

        //creat the variable of Mutable list of type contact
        val contact= mutableListOf<Contact>()

        val layoutManager = LinearLayoutManager(this)

        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding. rvContacts.layoutManager = layoutManager



        val model = ViewModelProvider(this)[LiveDataMainViewModel::class.java]

        model.getContacts().observe(this, Observer {
            Log.e(TAG, "Received contacts from view model")
            contact.addAll(it)
            //create the object of ContactAdapter class and set contact list into Constructor
            val contactAdapter= ContactAdapter(this,contact)

            //assign the object contactAdapter to recyclerview
            binding.rvContacts.adapter=contactAdapter
            contactAdapter.notifyDataSetChanged()
        })


    }


}