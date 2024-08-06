package com.example.star_wars.network.starshipnetwork


import android.util.Log
import com.example.star_wars.model.starshipmodel.Starship
import com.example.star_wars.network.RetrofitHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StarshipClient: IStarshipsRemoteSource {

    val starshipService: IStarshipService by lazy {
        RetrofitHelper.retrofitInstance.create(IStarshipService::class.java)
    }
    companion object
    {
        private var instance: StarshipClient?=null
        fun getInstance(): StarshipClient {
            return instance ?: synchronized(this){
                val temp= StarshipClient()
                instance =temp
                temp
            }
        }

    }

    override suspend fun getStarshipsFromNetwork(): Flow<List<Starship>> =flow {
        val starships = starshipService.getAllStarshipss().body()?.results?: listOf()
        Log.i("NET", "getStarshipsFromNetwork: client ${starships.get(1).name}")

        emit(starships)
    }


}