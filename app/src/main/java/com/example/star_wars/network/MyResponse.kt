package com.example.star_wars.network

import com.example.star_wars.model.Character

class MyResponse(val count: Long,
                 val next: String,
                 val previous: Any?,
                 val results: List<Character>)