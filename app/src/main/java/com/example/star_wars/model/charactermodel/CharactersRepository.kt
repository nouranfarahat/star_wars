package com.example.star_wars.model.charactermodel

import android.content.Context
import android.util.Log
import com.example.star_wars.network.characternetwork.ICharactersRemoteSource
import com.example.star_wars.utilities.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch

class CharactersRepository(var remoteSource: ICharactersRemoteSource,private val context: Context): ICharactersRepository {

    companion object
    {
        private var instance: CharactersRepository?=null
        fun getInstance(
            remoteSource: ICharactersRemoteSource,context: Context
        ): CharactersRepository {
            return instance ?: synchronized(this){
                val temp= CharactersRepository(remoteSource,context)
                instance =temp
                temp
            }
        }

    }

    override suspend fun getCharactersFromNetwork(): Flow<List<Character>> {

        if (!NetworkUtils.isNetworkAvailable(context)) {
            return flow { throw Exception("No internet connection") }
        }

        return remoteSource.getCharactersFromNetwork()
            .catch { e ->
                Log.e("REPO", "getCharactersFromNetwork: ${e.message}", e)
                throw e
            }
    }
        /*Log.i(
            "REPO",
            "getRecipesFromNetwork: repo ${remoteSource.getCharactersFromNetwork()}"
        )

        return remoteSource.getCharactersFromNetwork()*/
    }



