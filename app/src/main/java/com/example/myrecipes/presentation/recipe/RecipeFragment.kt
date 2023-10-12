package com.example.myrecipes.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.myrecipes.R
import br.com.myrecipes.databinding.FragmentRecipeBinding
import com.example.myrecipes.presentation.recipe.adapter.RecipeAdapter

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val adapter by lazy { RecipeAdapter() }
    private val viewModel: RecipeViewModel by viewModels { RecipeViewModel.Factory() }

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
        observeStates()
    }

    private fun setupListeners() {
        binding.fabRecipe.setOnClickListener {
            //@TODO SHOW DIALOG
        }
    }

    private fun setupAdapter() {
        binding.rvRecipes.adapter = adapter
    }

    private fun observeStates() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                RecipeState.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                RecipeState.Empty -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.recipe_empty_state_message),
                        Toast.LENGTH_LONG,
                    ).show()
                }
                is RecipeState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(state.recipes)
                }
                is RecipeState.Error -> {
                   binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        }
    }
}