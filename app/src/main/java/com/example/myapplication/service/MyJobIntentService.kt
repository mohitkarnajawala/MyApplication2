package com.example.myapplication.service

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import androidx.core.app.JobIntentService
import android.widget.Toast




class MyJobIntentService : JobIntentService() {

    /**
     * Unique job ID for this service.
     */
    val JOB_ID = 1000

    val mHandler: Handler = Handler()
    private val TAG = "MyJobIntentService"

    /**
     * Convenience method for enqueuing work in to this service.
     */
    fun enqueueWork(context: Context?, work: Intent?) {
        if (context != null) {
            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, work!!)
        }
    }

    override fun onCreate() {
        super.onCreate()
        showToast("Job Execution Started")
    }
    override fun onHandleWork(intent: Intent) {
        val maxCount = intent.getIntExtra("maxCountValue", -1)
        /**
         * Suppose we want to print 1 to 1000 number with one-second interval, Each task will take time 1 sec, So here now sleeping thread for one second.
         */
        /**
         * Suppose we want to print 1 to 1000 number with one-second interval, Each task will take time 1 sec, So here now sleeping thread for one second.
         */
        for (i in 0 until maxCount) {
            Log.e(TAG, "onHandleWork: The number is: $i")
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("Job Execution Finished")
    }

    // Helper for showing tests
    fun showToast(text: CharSequence?) {
        mHandler.post(Runnable {
            Toast.makeText(this@MyJobIntentService, text, Toast.LENGTH_SHORT).show()
        })
    }
}