package com.elmorshdi.movieexplorer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.elmorshdi.movieexplorer.R
import com.elmorshdi.movieexplorer.databinding.ItemBinding
import com.elmorshdi.movieexplorer.databinding.VerItemBinding
import com.elmorshdi.movieexplorer.model.Item
import com.elmorshdi.movieexplorer.ui.MoviesListFragment

class MovieAdapterHor(
    private val context: Context,
    private val movies: List<Item>,
    private val orientation: ListOrientation,
    private val interaction: Interaction
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class HorizontalItemViewHolder(
        private val binding: ItemBinding,
        private val interaction: Interaction
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Item,context: Context) {
            binding.title.text = movie.title
            Glide.with(context).load(movie.image)
                .apply(RequestOptions().centerCrop())
                .into(binding.poster)

            binding.executePendingBindings()

            binding.root.setOnClickListener {
                interaction.onItemSelected(movie,ListOrientation.HORIZONTAL)
            }
        }
    }


    class VerticalItemViewHolder(
        private val binding: VerItemBinding,
        private val interaction: Interaction
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Item,context: Context) {
            binding.title.text = movie.title
            Glide.with(context).load(movie.image)
                .apply(RequestOptions().centerCrop())
                .into(binding.poster)
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                interaction.onItemSelected(movie,ListOrientation.VERTICAL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        if (orientation == ListOrientation.HORIZONTAL)
            return HorizontalItemViewHolder(
                ItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                ), interaction
            )
        else //VERTICAL
        {
            return VerticalItemViewHolder(
                VerItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                ), interaction
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VerticalItemViewHolder)
            holder.bind(movies[position],context)
        else if (holder is HorizontalItemViewHolder)
           holder.bind(movies[position],context)

    }

    override fun getItemCount(): Int {
        return movies.size
    }


    enum class ListOrientation {
        HORIZONTAL, VERTICAL
    }

    //We use interface to notify the activity with every selection like onItemSelected , onBookmarkSelected
    interface Interaction {
        fun onItemSelected(movie: Item,type:ListOrientation)
    }
}