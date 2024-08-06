package com.example.star_wars.network

import com.example.star_wars.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ICharacterService {

    @GET("people")
    suspend fun getAllCharacters(): Response<MyResponse>
    @GET("people/{character_id}")
    suspend fun getCharacterInfo(@Path("character_id") id:Long): Response<Character> //Response<Character> //ProductResponse

}