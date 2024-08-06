package com.example.star_wars.starships.view

import com.example.star_wars.model.starshipmodel.Starship


//This interface to handle any clicks in the future
interface OnStarshipClickListener {
    //This function is to navigate from the character card to the character details and its implementation in the mainActivity
    fun onCardClick(starship: Starship)

}