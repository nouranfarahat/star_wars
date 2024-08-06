package com.example.star_wars.characters.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.star_wars.model.charactermodel.ICharactersRepository
import com.example.star_wars.model.charactermodel.Character
import com.example.star_wars.utilities.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AllCharactersViewModel(private val repo: ICharactersRepository) : ViewModel() {

    //Backing property
    private var _characters = MutableStateFlow<ApiState<List<Character>>>(ApiState.Loading)
    val characters: StateFlow<ApiState<List<Character>>>
        get() = _characters

    //When the object of viewModel is created fetchCharacters is called to present the recipe list to the user
    init {
        fetchCharacters()
    }

     fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCharactersFromNetwork()
                .catch {

                    error->_characters.value=ApiState.Failure(error)
                    Log.e("ViewModel", "fetchCharacters: ${error.message}", error)

                }
                .collect{
                    data->_characters.value=ApiState.Success(data)
                    //check if the data arrived here
                    Log.i("ViewModel", "fetchCharacters: ${data[0].name}")
                }


        }
    }

}