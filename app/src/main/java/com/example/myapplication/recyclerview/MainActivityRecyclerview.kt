package com.example.myapplication.recyclerview
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Constant.showToastMessage
import com.example.myapplication.databinding.ActivityMainRecyclerviewBinding
import com.example.myapplication.databinding.FragmentHomeBinding


class MainActivityRecyclerview : Fragment() {

    private val TAG = MainActivityRecyclerview::class.java.simpleName

    private lateinit var binding: ActivityMainRecyclerviewBinding

    private lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()



    val adapter = MainAdapter()

    /*@SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)*/

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.e(TAG,"Fragment:onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"Fragment:onCreate")
    }

    @SuppressLint("LongLogTag")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = ActivityMainRecyclerviewBinding.inflate(inflater, container, false)
        val root: View = binding.root



        viewModel = ViewModelProvider(this,
            MyViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "onError*: $it")
        })
        viewModel.getAllMovies()

        Log.e(TAG,"Fragment:onCreatView")

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"Fragment:onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(TAG,"Fragment:onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG,"Fragment:onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.e(TAG,"Fragment:onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.e(TAG,"Fragment:onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.e(TAG,"Fragment:onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG,"Fragment:onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"Fragment:onDestroy")
    }


    override fun onDetach() {
        super.onDetach()
        Log.e(TAG,"Fragment:onDetach")
    }
}