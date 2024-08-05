package com.example.star_wars.network

import com.example.star_wars.model.Result

class MyResponse(val count: Long,
                 val next: String,
                 val previous: Any?,
                 val results: List<Result>)