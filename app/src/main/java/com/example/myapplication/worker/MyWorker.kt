package com.example.myapplication.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context : Context, workerParameters: WorkerParameters) : Worker(context,workerParameters){
    override fun doWork(): Result {
        Log.e("WorkerThreead:-","Worker is working the task")
        return Result.success()
    }
}