package com.example.star_wars.network

import com.example.star_wars.model.Character
import kotlinx.coroutines.flow.Flow

interface IRemoteSource {

    suspend fun getCharactersFromNetwork(): Flow<List<Character>>
    suspend fun getCharacterInfoFromNetwork(id:Long): Flow<Character>

}