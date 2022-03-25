package com.example.myapplication.data

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.Constant.FirebaseAuthentication
import com.example.myapplication.data.model.LoggedInUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    /*
    * @Register the username and password to firebase Authentication
    * @param username   String
    * @parmam password   String
    * @return   result of Firebase user(Result<FirebaseUser>)
    * */
    fun register(username: String, password: String): Result<LoggedInUser> {

        try {
            var  firebase_user:FirebaseUser?=null

            // TODO: handle loggedInUser authentication
            FirebaseAuthentication.getFirebaseAuth()
                .createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.e("LoginDataSource:-","Success")
                    // Sign in success, update UI with the signed-in user's information
                    firebase_user = FirebaseAuthentication.getFirebaseAuth().currentUser
                    Log.e("User Firebase:-",""+firebase_user?.email)


                } else {
                    Log.e("LoginDataSource:-","Failed")
                    // If sign in fails, display a message to the user.
                    Log.e("Exception:-","Authentication failed.")
                }
            }

            Log.e("User Firebase:-",""+firebase_user?.email)

           var user=LoggedInUser( firebase_user?.uid.toString(),firebase_user?.email.toString() )
            return Result.Success(user)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

private fun <TResult> Task<TResult>.addOnCompleteListener(context: Context, activity: Activity) {

}

private fun Any.addOnCompleteListener(context: Context, activity: Activity) {

}
