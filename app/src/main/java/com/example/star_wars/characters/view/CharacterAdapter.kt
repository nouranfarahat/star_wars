package com.example.star_wars.characters.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.star_wars.R
import com.example.star_wars.databinding.CharacterCardBinding
import com.example.star_wars.model.Result

class CharacterAdapter(var listener: OnCharacterClickListener)
    : ListAdapter<Result, CharacterAdapter.ViewHolder>(CharacterDiffUtil()) {

    class ViewHolder(var characterBinding: CharacterCardBinding): RecyclerView.ViewHolder(characterBinding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowContentBinding: CharacterCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.character_card,parent,false)
        return ViewHolder(rowContentBinding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCharacter=getItem(position)
        holder.characterBinding.character=currentCharacter
        holder.characterBinding.action=listener
    }
}

class CharacterDiffUtil: DiffUtil.ItemCallback<Result>()
{
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.name==newItem.name
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {

        return oldItem==newItem
    }

}

