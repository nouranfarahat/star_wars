package com.example.star_wars.model.charactermodel

import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {

    suspend fun getCharactersFromNetwork(): Flow<List<Character>>


}