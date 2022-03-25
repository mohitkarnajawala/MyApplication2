package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.LiveDataExample.LiveDataMain
import com.example.myapplication.recyclerview.MainActivityRecyclerview
import com.example.myapplication.ui.login.LoginActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            val intent = Intent(this, MainDraweraActivity::class.java)
          //  val intent = Intent(this, LoginActivity::class.java)
            //val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            finish()
        }, 3000)


    }
}