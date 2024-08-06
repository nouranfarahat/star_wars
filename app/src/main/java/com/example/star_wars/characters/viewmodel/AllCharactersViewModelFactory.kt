package com.example.star_wars.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.star_wars.model.ICharactersRepository

class AllCharactersViewModelFactory(private val repo: ICharactersRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(AllCharactersViewModel::class.java))
        {
            AllCharactersViewModel(repo) as T
        }
        else
        {
            throw IllegalStateException("ViewModel Class not found")
        }
    }
}