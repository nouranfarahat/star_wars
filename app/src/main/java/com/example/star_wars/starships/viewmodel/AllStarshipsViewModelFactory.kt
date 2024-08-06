package com.example.star_wars.starships.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.star_wars.model.starshipmodel.IStarshipsRepository

class AllStarshipsViewModelFactory(private val repo: IStarshipsRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(AllStarshipsViewModel::class.java))
        {
            AllStarshipsViewModel(repo) as T
        }
        else
        {
            throw IllegalStateException("ViewModel Class not found")
        }
    }
}