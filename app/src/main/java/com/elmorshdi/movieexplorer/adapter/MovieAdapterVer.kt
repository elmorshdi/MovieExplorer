package com.elmorshdi.movieexplorer.adapter

import android.content.Context

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.elmorshdi.movieexplorer.R
import com.elmorshdi.movieexplorer.model.Item

class MovieAdapterVer(val context: Context) : RecyclerView.Adapter<MovieAdapterVer.MyViewHolder>() {

    var movieList : List<Item> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.ver_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvMovieName.text = movieList.get(position).title
        Glide.with(context).load(movieList.get(position).image)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
    }

    fun setMovieListItems(movieList: List<Item>){
        this.movieList = movieList;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val tvMovieName: TextView = itemView!!.findViewById(R.id.title)
        val image: ImageView = itemView!!.findViewById(R.id.poster)

    }
}


