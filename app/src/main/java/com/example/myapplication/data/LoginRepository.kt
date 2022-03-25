package com.example.myapplication.data

import android.content.Context
import android.util.Log
import com.example.myapplication.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    var _firebaseUser:FirebaseUser?=null
    val isLoggedIn: Boolean get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)
        Log.e("Login:result",""+result)
        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    /*
    * @Return the Result of Firebase User after Registered to Firebase
    * @param username  String
    * @param password  String
    * @param name  String
    * @return  result
    * */
    fun register(username: String, password: String,name: String): Result<LoggedInUser> {
        // handle registration on firebase
        //get result from data source after register to firebase authentication
        val result = dataSource.register(username, password)
        Log.e("LoginRepository:result",""+result)

        if (result is Result.Success) {
            Log.e("LoginRepository:-","Success")
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setFirebaseUser(firebaseUser: FirebaseUser){
        this._firebaseUser=firebaseUser
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}