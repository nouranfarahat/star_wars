package com.example.star_wars.network


import com.example.star_wars.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterClient:IRemoteSource {

    val characterService:ICharacterService by lazy {
        RetrofitHelper.retrofitInstance.create(ICharacterService::class.java)
    }
    companion object
    {
        private var instance:CharacterClient?=null
        fun getInstance(): CharacterClient {
            return instance?: synchronized(this){
                val temp=CharacterClient()
                instance=temp
                temp
            }
        }

    }


    override suspend fun getCharacterFromNetwork(): Flow<List<Result>> =flow {
        val characters = characterService.getAllCharacters().body()?: listOf()
        emit(characters)
    }


}