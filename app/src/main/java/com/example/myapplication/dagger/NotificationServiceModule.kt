package com.example.myapplication.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
//class NotificationServiceModule(private val retryCount: Int) { passing value to NotificationServiceModule constructor
class NotificationServiceModule() {  //when using Factory method in Component

    //Note: Here @Named annotation is required to declared to know the Dagger to call which Notification service
    // Because NotificationService Class  is Bind to both Message and Email Service
    //@Named annotations are know as Qualifiers

    // Incase if there is problem create of typo i:e spelling mistake in @Named annotation
    //we can create our own qualifier
    //@Named("message")
    @ActivityScope
    @MessageQualifier
    @Provides
    fun getMessageService(retryCount:Int): NotificationService{
        return MessageService(retryCount)
    }

    @ActivityScope
    @Named("email")
    @Provides
    fun getEmailService(emailService: EmailService): NotificationService{
        return emailService
    }
}