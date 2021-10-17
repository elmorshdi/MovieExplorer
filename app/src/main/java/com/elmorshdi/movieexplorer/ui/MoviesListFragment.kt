package com.elmorshdi.movieexplorer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.elmorshdi.movieexplorer.R
import com.elmorshdi.movieexplorer.adapter.MovieAdapterHor
import com.elmorshdi.movieexplorer.databinding.MoviesListFragmentBinding
import com.elmorshdi.movieexplorer.model.Item

private lateinit var viewModel: MoviesListViewModel
private lateinit var binding: MoviesListFragmentBinding
lateinit var navController: NavController

class MoviesListFragment : Fragment(), MovieAdapterHor.Interaction {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesListFragmentBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = Navigation.findNavController(binding.root)

        viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        viewModel.refresh()
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.mostPopular.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {


                binding.rvV.adapter =
                    MovieAdapterHor(
                        this.requireContext(),
                        it,
                        MovieAdapterHor.ListOrientation.VERTICAL,
                        this
                    )
            }
        })

        viewModel.movies.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {


                binding.rvH.adapter =
                    MovieAdapterHor(
                        this.requireContext(),
                        it,
                        MovieAdapterHor.ListOrientation.HORIZONTAL,
                        this
                    )
            }
        })


    }

    override fun onItemSelected(movie: Item,type: MovieAdapterHor.ListOrientation) {
        val bundle = bundleOf(
            "ITEM" to movie,
            "TYPE" to type
        )
        navController.navigate(R.id.action_moviesListFragment_to_movieDetailsFragment, bundle)
    }
}