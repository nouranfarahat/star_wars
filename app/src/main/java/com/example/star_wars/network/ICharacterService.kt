package com.example.star_wars.network

import com.example.star_wars.model.Result
import retrofit2.Response
import retrofit2.http.GET

interface ICharacterService {

    @GET("people")
    suspend fun getAllCharacters(): Response<List<Result>>
}