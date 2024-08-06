package com.example.star_wars.starships.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.star_wars.R
import com.example.star_wars.characters.view.CharactersFragmentDirections
import com.example.star_wars.databinding.FragmentStarshipsBinding
import com.example.star_wars.model.starshipmodel.Starship
import com.example.star_wars.model.starshipmodel.StarshipsRepository
import com.example.star_wars.network.starshipnetwork.StarshipClient
import com.example.star_wars.starships.viewmodel.AllStarshipsViewModel
import com.example.star_wars.starships.viewmodel.AllStarshipsViewModelFactory
import com.example.star_wars.utilities.ApiState
import com.example.star_wars.utilities.NetworkCallbackHandler
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StarshipsFragment : Fragment(),OnStarshipClickListener {

    lateinit var allStarshipsBinding: FragmentStarshipsBinding
    lateinit var viewModel: AllStarshipsViewModel
    lateinit var starshipsViewModelFactory: AllStarshipsViewModelFactory
    lateinit var starshipAdapter: StarshipAdapter
    lateinit var networkCallbackHandler: NetworkCallbackHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allStarshipsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_starships, container, false)
        starshipAdapter = StarshipAdapter(this)
        allStarshipsBinding.apply {
            adapter=starshipAdapter
            lifecycleOwner=this@StarshipsFragment
        }

        return allStarshipsBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        starshipsViewModelFactory= AllStarshipsViewModelFactory(
            StarshipsRepository.getInstance(
            StarshipClient.getInstance(),requireContext()))
        viewModel= ViewModelProvider(this,starshipsViewModelFactory).get(AllStarshipsViewModel::class.java)

        lifecycleScope.launch {
            viewModel.starships.collect { result ->
                when (result ) {
                    is ApiState.Success-> {
                        allStarshipsBinding.apply {
                            progressBar.visibility=View.GONE
                            starshipsRecyclerView.visibility = View.VISIBLE
                            starshipAdapter.submitList(result.data)
                        }
                    }
                    is ApiState.Loading->{
                        allStarshipsBinding.apply {
                            progressBar.visibility=View.VISIBLE
                            starshipsRecyclerView.visibility = View.GONE

                        }
                    }

                    is ApiState.Failure -> {
                        allStarshipsBinding.progressBar.visibility = View.GONE
                        withContext(Dispatchers.Main) {
                            getView()?.let { Snackbar.make(it, "No internet connection", Snackbar.LENGTH_LONG).show() }
                        }
                    }
                }
            }
        }
        networkCallbackHandler = NetworkCallbackHandler(
            requireContext(),
            onNetworkAvailable = { viewModel.fetchStarships() }, // Retry fetching data when network is available
            onNetworkLost = { /* Handle network lost if needed */ }
        )
        networkCallbackHandler.register()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        networkCallbackHandler.unregister()
    }


    override fun onCardClick(starship: Starship) {
        Toast.makeText(
            requireContext(),
            "${starship.name}",
            Toast.LENGTH_SHORT
        ).show()
        val action = StarshipsFragmentDirections.actionStarshipsFragmentToStarshipDetailsFragment(starship)
        findNavController().navigate(action)
    }



}