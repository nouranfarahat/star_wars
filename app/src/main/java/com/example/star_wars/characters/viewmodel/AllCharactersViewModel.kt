package com.example.star_wars.characters.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.star_wars.model.ICharactersRepository
import com.example.star_wars.model.Character
import com.example.star_wars.utilities.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AllCharactersViewModel(private val repo: ICharactersRepository) : ViewModel() {

    //Backing property
    private var _characters = MutableStateFlow<ApiState<List<Character>>>(ApiState.Loading)
    val recipes: StateFlow<ApiState<List<Character>>>
        get() = _characters

    //When the object of viewModel is created fetchCharacters is called to present the recipe list to the user
    init {
        fetchCharacters()
    }

     private fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCharactersFromNetwork()
                .catch {

                    error->_characters.value=ApiState.Failure(error)
                    Log.i("MUT", "fetchRecipes: viewmodel error")

                }
                .collect{
                    data->_characters.value=ApiState.Success(data)
                    //check if the data arrived here
                    Log.i("MUT", "fetchRecipes: viewmodel ${data.get(0).name}")
                }


        }
    }

}