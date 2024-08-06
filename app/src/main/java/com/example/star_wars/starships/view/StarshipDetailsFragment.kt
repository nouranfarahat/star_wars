package com.example.star_wars.starships.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.star_wars.R
import com.example.star_wars.characters.view.CharacterDetailsFragmentArgs
import com.example.star_wars.databinding.FragmentStarshipDetailsBinding
import com.example.star_wars.model.charactermodel.Character
import com.example.star_wars.model.starshipmodel.Starship


class StarshipDetailsFragment : Fragment() {
    lateinit var starshipDetailsBinding: FragmentStarshipDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        starshipDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_starship_details, container, false)
        val starshipDetails: Starship? = StarshipDetailsFragmentArgs.fromBundle(requireArguments()).starshipDetails
        starshipDetailsBinding.apply {
            starship=starshipDetails
            lifecycleOwner=this@StarshipDetailsFragment
        }

        return starshipDetailsBinding.root
    }


}