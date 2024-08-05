package com.example.star_wars.characters.view

import com.example.star_wars.model.Result


//This interface to handle any clicks in the future
interface OnCharacterClickListener {
    //This function is to navigate from the character card to the character details and its implementation in the mainActivity
    fun onCardClick(character: Result)

}