package com.example.star_wars.network.starshipnetwork

import retrofit2.Response
import retrofit2.http.GET

interface IStarshipService {

    @GET("starships")
    suspend fun getAllStarshipss(): Response<StarshipsResponse>


}