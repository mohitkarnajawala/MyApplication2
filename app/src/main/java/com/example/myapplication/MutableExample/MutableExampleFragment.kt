package com.example.myapplication.MutableExample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityMutableExampleBinding

class MutableExampleFragment : Fragment() {

    // View Binding
    private var _binding: ActivityMutableExampleBinding? = null
    private val binding get() = _binding!!

    // Create a viewModel
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=ActivityMutableExampleBinding.inflate(inflater,container,false)
        val view=binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListner()
        fragmentTextUpdateObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

   private fun setOnClickListner(){

       binding.fragmentButton.setOnClickListener{
           viewModel.getUpdatedData()
       }
    }

   private fun  fragmentTextUpdateObserver(){

       viewModel.uiTextLiveData.observe(viewLifecycleOwner, Observer {
           binding.fragmentTextView.text=it
       })
    }

}