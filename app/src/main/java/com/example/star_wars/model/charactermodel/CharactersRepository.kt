package com.example.star_wars.model.charactermodel

import android.util.Log
import com.example.star_wars.network.characternetwork.ICharactersRemoteSource
import kotlinx.coroutines.flow.Flow

class CharactersRepository(var remoteSource: ICharactersRemoteSource): ICharactersRepository {

    companion object
    {
        private var instance: CharactersRepository?=null
        fun getInstance(
            remoteSource: ICharactersRemoteSource,
        ): CharactersRepository {
            return instance ?: synchronized(this){
                val temp= CharactersRepository(remoteSource)
                instance =temp
                temp
            }
        }

    }

    override suspend fun getCharactersFromNetwork(): Flow<List<Character>> {

        Log.i(
            "REPO",
            "getRecipesFromNetwork: repo ${remoteSource.getCharactersFromNetwork()}"
        )

        return remoteSource.getCharactersFromNetwork()
    }



}