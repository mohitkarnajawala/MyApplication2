package com.example.myapplication.dagger

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(dependencies = [AppComponent::class],modules = [UserRepositoryModule::class, NotificationServiceModule::class])
interface UserRegistrationComponent {

    //create the object of UserRegistrationService
   // fun getUserRegistrationService() : UserRegistrationService

    //create the object of Email service class
   // fun getEmailService(): EmailService

    //1. In Dagger name doesn't matters of function it matter the datatype
    //create the function inject instead of making many other function
    //ignore the above methods now because object will be created by the function inject
    //Therefore comment the above functions
    //and pass the consumer to inject function i:e is ActivityDagger
    fun inject(activityDagger: ActivityDagger)

    //Get Component object via Factory
    /*@Component.Factory
    interface Factory{
       fun  Create(@BindsInstance retryCount:Int,appComponent: AppComponent):UserRegistrationComponent

    }*/

    //Get Component object via Builder
    @Component.Builder
    interface Builder{
        fun build():UserRegistrationComponent
        fun appComponent(appComponent: AppComponent):Builder
        fun retryCount(@BindsInstance retryCount: Int):Builder
    }
}