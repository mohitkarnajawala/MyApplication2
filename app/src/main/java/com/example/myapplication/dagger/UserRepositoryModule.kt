package com.example.myapplication.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UserRepositoryModule {

    //1. When Dagger doesn't creates object by itself then we use the @Provides annotation

    /*@Provides
    fun getFirebaseRepository():UserRepository{
        return FirebaseRepository()
    }*/

    /*@Provides
    fun getFirebaseRepository(sqlRepository: SqlRepository):UserRepository{
        return sqlRepository
    }*/

    //*****************************************************************************************

    //2. When Dagger Creates the Object by itself then no need to use @Provides annotation
    //Instead of @Provides annotation use @Binds annotation
    //ALl the function of @Binds annotations are Abstract because already dagger create Object of Class
    @Binds
    @ActivityScope
    abstract fun getFirebaseRepository(sqlRepository: SqlRepository):UserRepository
}
