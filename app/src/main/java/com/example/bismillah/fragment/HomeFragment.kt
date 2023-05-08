package com.example.bismillah.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bismillah.R
import com.example.bismillah.adapter.TopRatedAdapter
import com.example.bismillah.databinding.FragmentHomeBinding
import com.example.bismillah.viewmodel.TopRatedViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var pref: SharedPreferences
    private val ViewModel: TopRatedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getDataMovie()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)
        val username = pref.getString("username", "username")
        binding.tvWelcome.text = "Welcome, $username"


        //Profile
        binding.btnProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_profileFragment)
        }


    }
    override fun onStart(){
        super.onStart()

        ViewModel.getMovies().observe(requireActivity()) {
            val adapter = TopRatedAdapter(it)
            val layoutManager = GridLayoutManager(context,2)
            binding.rvTopRated.layoutManager = layoutManager
            binding.rvTopRated.adapter = adapter
        }

    }
}

