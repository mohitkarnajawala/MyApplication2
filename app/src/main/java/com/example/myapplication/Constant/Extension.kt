package com.example.myapplication.Constant

import android.content.Context
import android.widget.Toast

fun Context.showToastMessage( message:String,toast: Int=Toast.LENGTH_LONG){

    Toast.makeText(this,message,toast).show()
}