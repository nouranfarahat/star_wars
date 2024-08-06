package com.example.star_wars.characters.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.star_wars.R
import com.example.star_wars.characters.viewmodel.AllCharactersViewModel
import com.example.star_wars.characters.viewmodel.AllCharactersViewModelFactory
import com.example.star_wars.databinding.FragmentCharactersBinding
import com.example.star_wars.model.charactermodel.CharactersRepository
import com.example.star_wars.model.charactermodel.Character
import com.example.star_wars.network.characternetwork.CharacterClient
import com.example.star_wars.utilities.ApiState
import com.example.star_wars.utilities.NetworkCallbackHandler
import com.example.star_wars.utilities.NetworkReceiver
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersFragment : Fragment(), OnCharacterClickListener {
    lateinit var allCharactersBinding: FragmentCharactersBinding
    lateinit var viewModel: AllCharactersViewModel
    lateinit var charactersFactory: AllCharactersViewModelFactory
    lateinit var charactersAdapter: CharacterAdapter
    lateinit var networkCallbackHandler: NetworkCallbackHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        allCharactersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        charactersAdapter = CharacterAdapter(this)
        allCharactersBinding.apply {
            adapter = charactersAdapter
            lifecycleOwner = this@CharactersFragment
        }

        return allCharactersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersFactory = AllCharactersViewModelFactory(
            CharactersRepository.getInstance(
                CharacterClient.getInstance(), requireContext()
            )
        )
        viewModel = ViewModelProvider(this, charactersFactory).get(AllCharactersViewModel::class.java)

        lifecycleScope.launch {
            viewModel.characters.collect { result ->
                when (result) {
                    is ApiState.Success -> {
                        allCharactersBinding.apply {
                            progressBar.visibility = View.GONE
                            charactersRecyclerView.visibility = View.VISIBLE
                            charactersAdapter.submitList(result.data)
                        }
                    }
                    is ApiState.Loading -> {
                        allCharactersBinding.apply {
                            progressBar.visibility = View.VISIBLE
                            charactersRecyclerView.visibility = View.GONE
                        }
                    }
                    is ApiState.Failure -> {
                        allCharactersBinding.progressBar.visibility = View.GONE
                        withContext(Dispatchers.Main) {
                            getView()?.let { Snackbar.make(it, "No internet connection", Snackbar.LENGTH_LONG).show() }
                        }
                    }
                }
            }
        }

        networkCallbackHandler = NetworkCallbackHandler(
            requireContext(),
            onNetworkAvailable = { viewModel.fetchCharacters() }, // Retry fetching data when network is available
            onNetworkLost = { /* Handle network lost if needed */ }
        )
        networkCallbackHandler.register()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        networkCallbackHandler.unregister()
    }

    override fun onCardClick(character: Character) {
        Toast.makeText(
            requireContext(),
            "${character.name}",
            Toast.LENGTH_SHORT
        ).show()
        val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(character)
        findNavController().navigate(action)
    }
}
