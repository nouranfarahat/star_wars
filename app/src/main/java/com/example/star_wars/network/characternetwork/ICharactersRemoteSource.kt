package com.example.star_wars.network.characternetwork

import com.example.star_wars.model.charactermodel.Character
import kotlinx.coroutines.flow.Flow

interface ICharactersRemoteSource {

    suspend fun getCharactersFromNetwork(): Flow<List<Character>>
    suspend fun getCharacterInfoFromNetwork(id:Long): Flow<Character>

}