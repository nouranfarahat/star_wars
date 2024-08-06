package com.example.star_wars.starships.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.star_wars.model.starshipmodel.IStarshipsRepository
import com.example.star_wars.model.starshipmodel.Starship
import com.example.star_wars.utilities.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AllStarshipsViewModel(private val repo: IStarshipsRepository) : ViewModel() {

    //Backing property
    private var _starships = MutableStateFlow<ApiState<List<Starship>>>(ApiState.Loading)
    val starships: StateFlow<ApiState<List<Starship>>>
        get() = _starships

    //When the object of viewModel is created fetchCharacters is called to present the recipe list to the user
    init {
        fetchStarships()
    }

     fun fetchStarships() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getStarshipsFromNetwork()
                .catch {

                    error->_starships.value=ApiState.Failure(error)
                    Log.i("MUT", "fetchRecipes: viewmodel error")

                }
                .collect{
                    data->_starships.value=ApiState.Success(data)
                    //check if the data arrived here
                    Log.i("MUT", "fetchRecipes: viewmodel ${data.get(0).name}")
                }


        }
    }

}