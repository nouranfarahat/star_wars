package com.example.star_wars.network.characternetwork

import com.example.star_wars.model.charactermodel.Character

class CharactersResponse(val count: Long,
                         val next: String,
                         val previous: Any?,
                         val results: List<Character>)