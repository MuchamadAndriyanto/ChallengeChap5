package com.example.bismillah.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.bismillah.R
import com.example.bismillah.databinding.FragmentDetailBinding
import com.example.bismillah.model.DetailTopRated


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // pengambilan data
        val list = arguments?.getParcelable<DetailTopRated>("data_toprated") as DetailTopRated
        val title = list.title
        val date = list.date
        val overview = list.overview
        val imagepath = list.imagepath

        binding.tvNamaMovie.text = title
        binding.tvDate.text = date
        binding.tvOverview.text = overview
        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${imagepath}").into(binding.ivDetailGambar)

    }

}
