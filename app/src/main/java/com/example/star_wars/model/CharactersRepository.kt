package com.example.star_wars.model

import android.util.Log
import com.example.star_wars.network.IRemoteSource
import kotlinx.coroutines.flow.Flow

class CharactersRepository(var remoteSource: IRemoteSource):ICharactersRepository {

    companion object
    {
        private var instance: CharactersRepository?=null
        fun getInstance(
            remoteSource: IRemoteSource,
        ): CharactersRepository {
            return instance?: synchronized(this){
                val temp= CharactersRepository(remoteSource)
                instance=temp
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

    override suspend fun getCharacterInfoResponseFromNetwork(id: Long): Flow<Character> {
        return remoteSource.getCharacterInfoFromNetwork(id)
    }

}