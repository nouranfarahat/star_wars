package com.example.star_wars.network.starshipnetwork

import com.example.star_wars.model.starshipmodel.Starship
import kotlinx.coroutines.flow.Flow

interface IStarshipsRemoteSource {

    suspend fun getStarshipsFromNetwork(): Flow<List<Starship>>

}