package com.example.star_wars.network


import android.util.Log
import com.example.star_wars.model.Character
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


    override suspend fun getCharactersFromNetwork(): Flow<List<Character>> =flow {
        val characters = characterService.getAllCharacters().body()?.results?: listOf()
        Log.i("NET", "getCharactersFromNetwork: client ${characters.get(1).name}")

        emit(characters)
    }

    override suspend fun getCharacterInfoFromNetwork(id:Long): Flow<Character> =flow {
        val character = characterService.getCharacterInfo(id).body()

        if (character != null) {
            emit(character)
        }
    }


}