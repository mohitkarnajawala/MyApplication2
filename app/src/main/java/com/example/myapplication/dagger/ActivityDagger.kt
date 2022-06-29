package com.example.myapplication.dagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.application.MyApplication
import com.example.myapplication.dagger.DaggerAppComponent.builder
import javax.inject.Inject

class ActivityDagger : AppCompatActivity() {

    @Inject
    lateinit var userRegistrationService:UserRegistrationService

    @Inject
    lateinit var emailService: EmailService

    @Inject
    lateinit var emailService1: EmailService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        //1. manual object Creation

        // val userRepository=UserRepository()
        //val emailService=EmailService()

        //manual dependency injection
        //val userRegistrationService= UserRegistrationService(userRepository,emailService)

        //****************************************************************************************


        //2. Make component to create the object by Dagger
       /* val component=DaggerUserRegistrationComponent.builder()               //Without using Factory in Component
            .notificationServiceModule(NotificationServiceModule(4))
            .build()*/

       // val component=DaggerUserRegistrationComponent.factory().Create(4) //With using Factory in Component

        //**************************************************************************************
        //3. Use UserRegistrationComponent from MyApplication
       // val component= (application as MyApplication).userRegistrationComponent

        //Now Instead of defining UserRegistrationComponent in MyApplication define AppComponent Object
        val appComponent= (application as MyApplication).appComponent

        //Incase of Factory
        //val userRegistrationComponent=DaggerUserRegistrationComponent.factory().Create(4,appComponent)

        //Incase of Builder
        val userRegistrationComponent=DaggerUserRegistrationComponent
            .builder()
            .appComponent(appComponent)
            .retryCount(3)
            .build()


        //Now the object of UserRegistrationService Class will be created by Dagger
        //val userRegistrationService= component.getUserRegistrationService()

        //create object of EmailService Class by Using Component
        //val emailService=component.getEmailService()

        //***************************************************************************************

        //3. Creating the object using inject method
        userRegistrationComponent.inject(this)
        userRegistrationService.register("hritikkaranjawala@gmail.com","12345")

    }
}

