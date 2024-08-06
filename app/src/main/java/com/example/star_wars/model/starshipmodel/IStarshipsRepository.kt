package com.example.star_wars.model.starshipmodel

import kotlinx.coroutines.flow.Flow

interface IStarshipsRepository {
    suspend fun getStarshipsFromNetwork(): Flow<List<Starship>>
}