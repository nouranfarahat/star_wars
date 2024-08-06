package com.example.star_wars.model

import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    suspend fun getCharactersFromNetwork(): Flow<List<Character>>
    suspend fun getCharacterInfoResponseFromNetwork(id:Long): Flow<Character>


}