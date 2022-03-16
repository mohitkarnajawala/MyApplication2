package com.example.myapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private  lateinit var  profileViewModel: ProfileViewModel
    private var _binding:FragmentProfileBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileViewModel= ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        val root : View = _binding!!.root

        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            _binding!!.textProfile.text= it

        })

        return root
    }
}