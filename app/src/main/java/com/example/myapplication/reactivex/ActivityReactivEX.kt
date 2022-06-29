package com.example.myapplication.reactivex

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers


class ActivityReactivEX : AppCompatActivity() {

     private lateinit var animalsObservable : Observable<String>

     private lateinit var animalsObserver:Observer<String>

     private lateinit var binding: FragmentHomeBinding

     var TAG: String = ActivityReactivEX::class.java.simpleName

    private lateinit var  disposable: Disposable

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding= FragmentHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //observable
        animalsObservable = getAnimalsObservable()

        //observer
        animalsObserver=  getAnimalsObserver()


        // observer subscribing to observable
        animalsObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { s -> s.lowercase().startsWith("b") }
            .subscribeWith(animalsObserver)
    }

    private fun getAnimalsObservable(): Observable<String> {
        return Observable.just("Ant", "Bee","Bear", "Cat", "Dog", "Fox")
    }

    private fun getAnimalsObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "onSubscribe")
                disposable=d
            }

            override fun onNext(s: String) {
                Log.e(TAG, "Name: $s")
                binding.textHome.text=s
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.e(TAG, "All items are emitted!")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}