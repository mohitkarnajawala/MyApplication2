package com.example.myapplication.dagger

import javax.inject.Inject
import javax.inject.Named

class UserRegistrationService @Inject constructor(private val userRepository: UserRepository ,
     @MessageQualifier private val notificationService: NotificationService) {

    fun register(email:String , password:String){
        userRepository.saveUser(email, password)
        notificationService.send(email, "mohitkaranjawala86@gmail.com","Its a Dagger practice")
    }
}