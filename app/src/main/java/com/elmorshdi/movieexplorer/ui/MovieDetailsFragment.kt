package com.elmorshdi.movieexplorer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.elmorshdi.movieexplorer.R
import com.elmorshdi.movieexplorer.adapter.MovieAdapterHor
import com.elmorshdi.movieexplorer.databinding.MovieDetailsFragmentBinding
import com.elmorshdi.movieexplorer.model.Item

class MovieDetailsFragment : Fragment() {
    lateinit var movie: Item
   lateinit var type:MovieAdapterHor.ListOrientation
    private lateinit var binding: MovieDetailsFragmentBinding
    private lateinit var viewModel: MovieDetailsViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.get("ITEM") as Item
        type = arguments?.get("TYPE") as MovieAdapterHor.ListOrientation

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = Navigation.findNavController(binding.root)

        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        Glide.with(this.requireContext()).load(movie.image)
            .apply(RequestOptions().autoClone())
            .into(binding.poster)
        binding.title.text = movie.title
        binding.tagline.text = movie.fullTitle
        binding.story.text = setText(type)
        binding.backButton.setOnClickListener {
              navController.navigate(R.id.action_movieDetailsFragment_to_moviesListFragment)

        }
    }
    fun setText(type:MovieAdapterHor.ListOrientation):String{
        lateinit var s:String
        if (type.equals(MovieAdapterHor.ListOrientation.HORIZONTAL)){
            s="Genres : ${movie.genres} \n \nStars : ${movie.stars}" +
                "\n \n${movie.plot}"}
        else{s="rank : ${movie.rank} \n \ncrew :  ${movie.crew} \n" +
                " \n" +
                "imDbRating :  ${movie.imDbRating} ⭐"}
        return s
    }
}
