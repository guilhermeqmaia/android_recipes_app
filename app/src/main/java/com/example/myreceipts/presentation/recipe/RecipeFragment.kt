package com.example.myreceipts.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.myrecipes.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

}