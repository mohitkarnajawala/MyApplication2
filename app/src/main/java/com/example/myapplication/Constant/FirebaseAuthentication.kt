package com.example.myapplication.Constant

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase

class FirebaseAuthentication {


    companion object{
        private var auth: FirebaseAuth?=null

        fun getFirebaseAuth():FirebaseAuth{

            if(auth==null){
                // Initialize Firebase Auth
                auth = Firebase.auth
            }
            return auth!!
        }

    }
}