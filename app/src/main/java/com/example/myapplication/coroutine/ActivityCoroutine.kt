package com.example.myapplication.coroutine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class ActivityCoroutine : AppCompatActivity() {

    private val TAG= ActivityCoroutine::class.java.simpleName
    private lateinit var binding: ActivityCoroutineBinding
    var  counter = 0

    private lateinit var  activityCoroutineModel: ActivityCoroutineModel
    var fb=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e(TAG,"${Thread.currentThread().name}")

        //create view model object
        activityCoroutineModel= ViewModelProvider(this).get(ActivityCoroutineModel::class.java)

        //lifecycle Scope Coroutine finish the activity and destroy the corountine inside viewmodel
        lifecycleScope.launch {
            delay(3000)
            var  intent= Intent(this@ActivityCoroutine,ActivityAnotherCoroutine::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnUpdate.setOnClickListener {
            updateCounter()
        }

        binding.btnExecute.setOnClickListener {
            //executeTask()
            doAction()
        }

        binding.btnMove.setOnClickListener {

        }

        //***************************************************************************
        //2. Use yield() method is for suspending the function
        //calling function Task1 from coroutine
        CoroutineScope(Dispatchers.IO).launch {
          //  Task1()
        }

        //calling function Task2 from coroutine
        CoroutineScope(Dispatchers.IO).launch {
            //Task2()
        }

        //****************************************************************************
        //3.  Launch  Concept
        //Launch :- It creates the Coroutines for the task
        //Use Job for suspend the Coroutine
        CoroutineScope(Dispatchers.IO).launch {
           // printFollowers()
        }


        //******************************************************************
        //4. Async Concept
        // It returns the Deffered Object
        //Job Hierarchy
        CoroutineScope(Dispatchers.Main).launch {
            //executeTask2()
        }

        //********************************************************************
        //5. Coroutine withContext,
        // it blocks the task or run task sequential order
        GlobalScope.launch {
           // getExecuteTask()

        }

        //**********************************************************************
        //6. runBlocking{} coroutine
        runBlocking {
            launch {
                delay(1000)
                Log.e(TAG, "Hello ")
            }
        }
        Log.e(TAG,"World")

    }

    /*
    * 4. Job Hierarchy is implement parent and Child Relationship
    * */
    private suspend fun executeTask2() {
        //parent
        val sumOfNumber= CoroutineScope(Dispatchers.Main).launch {

            Log.e("Sum","Sum of Two Number Calculation is Started")

            //child
            val multiplyOfNumber= launch(Dispatchers.IO) {
                Log.e("Multiply","Multiply of Two Number Calculation is Started")
                delay(2000)
                Log.e("Multiply of Two number is:-","${multTwoNumber()}")
                Log.e("Multiply of Two number Calculation is:-","End")
            }
            delay(3000)
            Log.e("Sum of Two Number is :-","${sumTwoNumber()}")
            Log.e("Sum of Two Number Calculation is :-","End")

        }
        sumOfNumber.join()
        Log.e("Parent End","Parent Coroutine is Completed")


    }

    /*
    * Sum of Two number
    * */
    private fun sumTwoNumber():Int{
        var a=20
        var b=30
        return (a+b)
    }

    /*
    * Multiplication of Two number
    * */
    private fun multTwoNumber():Int{
        var x0= 12
        var x1= 5
        return (x0*x1)
    }



    private fun executeTask() {
        thread(start=true){
            for (i in 1..1000000000L){

            }
        }

    }

    /*
    * Different Coroutines works with Different Threads
    * */
    private fun doAction(){
        //Coroutine scope
        CoroutineScope(Dispatchers.IO).launch {
            Log.e(TAG,"1-> ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG,"2-> ${Thread.currentThread().name}")
        }

        MainScope().launch(Dispatchers.Default) {
            Log.e(TAG,"3-> ${Thread.currentThread().name}")
        }
    }

    /*
    * Update the UI with Counter value
    * */
    private fun updateCounter() {
        binding.txtCounter.text = (counter++).toString()
        Log.e(TAG,"${Thread.currentThread().name}")

    }

    /*
    * Used the Yield() method
    * */
    suspend fun Task1(){

        Log.e(TAG," Starting the Task 1")
        yield()
        Log.e(TAG," Ending the Task 1")
    }

    suspend fun Task2(){

        Log.e(TAG," Starting the Task 2")
        yield()
        Log.e(TAG," Ending the Task 2")
    }

    /*
    * @return  the Facebook Followers
    * */
    private suspend fun printFollowers() {
        var facebook=0
        var job=CoroutineScope(Dispatchers.IO).launch {
            facebook=getFacebookFollower()

        }
        job.join()
        Log.e("Facebook Followers are :-","${facebook}")
    }

    suspend fun getFacebookFollower():Int{
        delay(1000)
        return 300
    }

    /*
    * Working of coroutine in a particular Context
    * It works sequentially
    * */
    private suspend fun getExecuteTask(){
        Log.e(TAG,"Starting the task")
        withContext(Dispatchers.IO){
            delay(1000)
            Log.e(TAG,"Inside the task")
        }
        Log.e(TAG," Ending the task")
    }


}