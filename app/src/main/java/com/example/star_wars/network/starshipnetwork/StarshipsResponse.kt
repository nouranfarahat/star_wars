package com.example.star_wars.network.starshipnetwork

import com.example.star_wars.model.starshipmodel.Starship

class StarshipsResponse(val count: Long,
                        val next: String,
                        val previous: Any?,
                        val results: List<Starship>)