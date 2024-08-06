package com.example.star_wars.model.starshipmodel

import android.content.Context
import android.util.Log
import com.example.star_wars.network.starshipnetwork.IStarshipsRemoteSource
import com.example.star_wars.utilities.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class StarshipsRepository (var remoteSource: IStarshipsRemoteSource,private val context: Context): IStarshipsRepository {

    companion object
    {
        private var instance: StarshipsRepository?=null
        fun getInstance(
            remoteSource: IStarshipsRemoteSource,context: Context
        ): StarshipsRepository {
            return instance ?: synchronized(this){
                val temp= StarshipsRepository(remoteSource,context)
                instance =temp
                temp
            }
        }

    }

    override suspend fun getStarshipsFromNetwork(): Flow<List<Starship>> {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            return flow { throw Exception("No internet connection") }
        }

        return remoteSource.getStarshipsFromNetwork()
            .catch { e ->
                Log.e("REPO", "getStarshipsFromNetwork: ${e.message}", e)
                throw e
            }
    }


}