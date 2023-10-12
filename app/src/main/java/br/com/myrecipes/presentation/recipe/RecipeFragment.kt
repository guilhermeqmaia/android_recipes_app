package br.com.myrecipes.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import br.com.myrecipes.R
import br.com.myrecipes.databinding.FragmentRecipeBinding
import br.com.myrecipes.presentation.dialog.DialogEditTextFragment
import br.com.myrecipes.presentation.recipe.adapter.RecipeAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

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
            showDialog()
        }

        adapter.click = { recipeItem ->
            val action = RecipeFragmentDirections.goToDetail(
                recipeItem.id
            )
            findNavController().navigate(action)
        }
    }

    private fun setupAdapter() {
        binding.rvRecipes.adapter = adapter
    }

    private fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@observe.collect(observe)
            }
        }
    }

    private fun observeStates() {

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                RecipesState.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                RecipesState.Empty -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.recipe_empty_state_message),
                        Toast.LENGTH_LONG,
                    ).show()
                }
                is RecipesState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(state.recipes)
                }
                is RecipesState.Error -> {
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

    private fun showDialog() {
        DialogEditTextFragment.show(
            title = getString(R.string.recipe_dialog_title),
            placeholder = getString(R.string.recipe_dialog_placeholder),
            fragmentManager = parentFragmentManager,
        )
        setFragmentResultListener(DialogEditTextFragment.FRAGMENT_RESULT) { requestKey: String, bundle: Bundle ->
            val recipeName = bundle.getString(DialogEditTextFragment.EDIT_TEXT_VALUE) ?: ""
            viewModel.insertRecipe(recipeName)
        }
    }


}