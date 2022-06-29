package com.example.myapplication.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.JobIntentService.enqueueWork

import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityJobIntentServiceBinding
import com.example.myapplication.service.LocalService
import com.example.myapplication.service.MyJobIntentService




class ActivityJobIntentService : AppCompatActivity(){

    private lateinit var binding:ActivityJobIntentServiceBinding

    private lateinit var mService: LocalService
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as LocalService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityJobIntentServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonJobIntent.setOnClickListener {
            onStartJobIntentService()
        }


    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        Intent(this, LocalService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }


    private fun onStartJobIntentService() {
       /* val mIntent = Intent(this, MyJobIntentService::class.java)
        mIntent.putExtra("maxCountValue", 1000)*/
        //MyJobIntentService.enqueueWork(this, mIntent)

        if (mBound) {
            // Call a method from the LocalService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            val num: Int = mService.randomNumber

            Toast.makeText(this, "number: $num", Toast.LENGTH_SHORT).show()
        }

    }


}