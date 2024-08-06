package com.example.star_wars.network.characternetwork

import com.example.star_wars.model.charactermodel.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ICharacterService {

    @GET("people")
    suspend fun getAllCharacters(): Response<CharactersResponse>
    @GET("people/{character_id}")
    suspend fun getCharacterInfo(@Path("character_id") id:Long): Response<Character> //Response<Character> //ProductResponse

}