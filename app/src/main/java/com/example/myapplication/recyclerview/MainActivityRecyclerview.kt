package com.example.myapplication.recyclerview
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainRecyclerviewBinding
import com.example.myapplication.databinding.FragmentHomeBinding


class MainActivityRecyclerview : Fragment() {

    private val TAG = "MainActivityRecyclerview"

    private lateinit var binding: ActivityMainRecyclerviewBinding

    private lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    val adapter = MainAdapter()

    /*@SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)*/
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

        return root
    }


}