package com.example.star_wars.characters.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.star_wars.R
import com.example.star_wars.databinding.FragmentCharacterDetailsBinding
import com.example.star_wars.model.charactermodel.Character


class CharacterDetailsFragment : Fragment() {
    lateinit var characterDetailsBinding: FragmentCharacterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        characterDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_details, container, false)
        val characterDetails: Character? = CharacterDetailsFragmentArgs.fromBundle(requireArguments()).characterDetails
        characterDetailsBinding.apply {
            character=characterDetails
            lifecycleOwner=this@CharacterDetailsFragment
        }

        return characterDetailsBinding.root
    }

}