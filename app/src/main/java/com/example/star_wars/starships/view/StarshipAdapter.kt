package com.example.star_wars.starships.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.star_wars.R
import com.example.star_wars.databinding.StarshipCardBinding
import com.example.star_wars.model.starshipmodel.Starship

class StarshipAdapter(var listener: OnStarshipClickListener)
    : ListAdapter<Starship, StarshipAdapter.ViewHolder>(StarshipDiffUtil()) {

    class ViewHolder(var starshipCardBinding: StarshipCardBinding): RecyclerView.ViewHolder(starshipCardBinding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowContentBinding: StarshipCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.starship_card,parent,false)
        return ViewHolder(rowContentBinding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentStarship=getItem(position)
        holder.starshipCardBinding.starship=currentStarship
        holder.starshipCardBinding.action=listener
    }
}

class StarshipDiffUtil: DiffUtil.ItemCallback<Starship>()
{
    override fun areItemsTheSame(oldItem: Starship, newItem: Starship): Boolean {
        return oldItem.name==newItem.name
    }

    override fun areContentsTheSame(oldItem: Starship, newItem: Starship): Boolean {

        return oldItem==newItem
    }

}

