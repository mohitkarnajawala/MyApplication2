package com.example.myapplication.Constant

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun Context.showToastMessage( message:String,toast: Int=Toast.LENGTH_LONG){

    Toast.makeText(this,message,toast).show()
}

 fun Context.bitmapDescriptorFromVector(context: Context?, vectorResId: Int): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(context!!, vectorResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap =
        Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}