package com.example.myrecipes.presentation.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.myrecipes.databinding.ItemRecipeBinding
import com.example.myrecipes.domain.model.RecipeModel

class RecipeAdapter : ListAdapter<RecipeModel, RecipeAdapter.ViewHolder>(DiffCallback()) {

    var click: (RecipeModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeModel) {
            binding.root.setOnClickListener{
                click(item)
            }
            binding.tvTitle.text = item.name
        }

    }

}

class DiffCallback : DiffUtil.ItemCallback<RecipeModel>() {
    override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean =
        oldItem.id == newItem.id

}