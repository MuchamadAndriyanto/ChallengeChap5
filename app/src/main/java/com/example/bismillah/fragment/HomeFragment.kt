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
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        val fullname = pref.getString("username", "username")
        binding.welcome.text = "Welcome, $fullname!"


        //Profile
        binding.btnProfile.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_profileFragment)
        }


    }

    override fun onStart(){
        super.onStart()

        Log.d("Tag", "Fragment activity : datanya ->")
        ViewModel.getMovies().observe(requireActivity()) {
            Log.d("Tag", "Fragment activity : datanya -> $it")
            val adapter = TopRatedAdapter(it)
            val layoutManager = GridLayoutManager(context,2)
            binding.rvFilm.layoutManager = layoutManager
            binding.rvFilm.adapter = adapter
        }

    }
}

