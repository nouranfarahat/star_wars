package com.example.star_wars.model.starshipmodel

import com.example.star_wars.network.starshipnetwork.IStarshipsRemoteSource
import kotlinx.coroutines.flow.Flow

class StarshipsRepository (var remoteSource: IStarshipsRemoteSource): IStarshipsRepository {

    companion object
    {
        private var instance: StarshipsRepository?=null
        fun getInstance(
            remoteSource: IStarshipsRemoteSource,
        ): StarshipsRepository {
            return instance ?: synchronized(this){
                val temp= StarshipsRepository(remoteSource)
                instance =temp
                temp
            }
        }

    }

    override suspend fun getStarshipsFromNetwork(): Flow<List<Starship>> {
        return remoteSource.getStarshipsFromNetwork()
    }


}