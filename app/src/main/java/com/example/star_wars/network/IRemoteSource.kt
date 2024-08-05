package com.example.star_wars.network

import com.example.star_wars.model.Result
import kotlinx.coroutines.flow.Flow

interface IRemoteSource {

    suspend fun getCharacterFromNetwork(): Flow<List<Result>>
}