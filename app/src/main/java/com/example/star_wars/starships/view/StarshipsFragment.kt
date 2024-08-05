package com.example.star_wars.starships.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.star_wars.R

/**
 * A simple [Fragment] subclass.
 * Use the [StarshipsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StarshipsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_starships, container, false)
    }


}