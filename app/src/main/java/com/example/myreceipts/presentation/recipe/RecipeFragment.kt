package com.example.myreceipts.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.myrecipes.databinding.FragmentRecipeBinding
import com.example.myreceipts.presentation.recipe.adapter.RecipeAdapter

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val adapter by lazy { RecipeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupAdapter()
    }

    private fun setupListeners() {
        binding.fabRecipe.setOnClickListener {
            //@TODO SHOW DIALOG
        }
    }

    private fun setupAdapter() {
        binding.rvRecipes.adapter = adapter
    }

}