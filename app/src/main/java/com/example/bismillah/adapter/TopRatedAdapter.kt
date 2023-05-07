package com.example.bismillah.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bismillah.R
import com.example.bismillah.databinding.ItemTopratedBinding
import com.example.bismillah.model.DetailTopRated
import com.example.bismillah.model.Result

class TopRatedAdapter(var listtopRated : List<Result>) : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemTopratedBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTopratedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = listtopRated[position].title
        holder.binding.tvDate.text = listtopRated[position].releaseDate
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w780${listtopRated[position].backdropPath}").into(holder.binding.imgView)

        holder.binding.detailToprated.setOnClickListener {
            var imagepath = listtopRated[position].backdropPath
            var title = listtopRated[position].title
            var date = listtopRated[position].releaseDate
            var overview = listtopRated[position].overview
            var detail = DetailTopRated(imagepath,title,date, overview)

            val data = Bundle()
            data.putParcelable("data_toprated",detail)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment,data)

        }

    }

    override fun getItemCount(): Int {
        return listtopRated.size
    }

}