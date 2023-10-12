package br.com.myrecipes.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.myrecipes.R
import br.com.myrecipes.databinding.FragmentDetailBinding
import br.com.myrecipes.domain.model.IngredientModel
import br.com.myrecipes.domain.model.PrepareMode
import br.com.myrecipes.presentation.detail.adapter.ItemListAdapter
import br.com.myrecipes.presentation.dialog.DialogEditTextFragment
import br.com.myrecipes.presentation.model.ItemListModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<ItemListViewModel> {
        ItemListViewModel.Factory()
    }

    private val adapterIngredients by lazy { ItemListAdapter() }
    private val adapterPrepareMode by lazy { ItemListAdapter() }

    private var typeInsert = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observe()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAddIngredient.setOnClickListener {
            showDialogNewIngredient()
        }
        binding.btnAddPrepareMode.setOnClickListener {
            showDialogNewPrepareMode()
        }

        setFragmentResultListener(DialogEditTextFragment.FRAGMENT_RESULT) { requestKey, bundle ->
            val name = bundle.getString(DialogEditTextFragment.EDIT_TEXT_VALUE) ?: ""
            viewModel.insertIngredientsOrPrepareMode(
                typeInsert = typeInsert,
                name = name,
                recipeId = args.recipeId
            )
            if (typeInsert == "INGREDIENT") {
                adapterIngredients.addItem(ItemListModel(id = 0, name = name))
            } else {
                adapterPrepareMode.addItem(ItemListModel(id = 0, name = name))
            }
        }
        adapterIngredients.edit = {
            viewModel.updateIngredient(
                IngredientModel(
                    id = it.id,
                    name = it.name,
                    recipeId = args.recipeId,
                )
            )
        }
        adapterIngredients.remove = {
           viewModel.removeIngredient(
               IngredientModel(
                   id = it.id,
                   name = it.name,
                   recipeId = args.recipeId,
               )
           )
        }

        adapterPrepareMode.edit = {
            viewModel.updatePrepareMode(
                PrepareMode(
                    id = it.id,
                    name = it.name,
                    recipeId = args.recipeId,
                )
            )
        }

        adapterPrepareMode.remove = {
            viewModel.removePrepareMode(
                PrepareMode(
                    id = it.id,
                    name = it.name,
                    recipeId = args.recipeId,
                )
            )
        }
    }

    private fun observe() {
        viewModel.getRecipeWithIngredientsAndPrepareMode(args.recipeId)
            .observe(viewLifecycleOwner) {
                when (it) {
                    ItemListState.Loading -> {

                    }
                    is ItemListState.Error -> {
                        Toast.makeText(requireContext(), "Ops, ocorreu um erro ao trazer os dados", Toast.LENGTH_LONG).show()
                    }
                    is ItemListState.Success -> {
                        adapterIngredients.submitList(it.ingredients)
                        adapterPrepareMode.submitList(it.prepareMode)
                    }
                }
            }
    }

    private fun setupAdapter() {
        binding.rvIngredients.adapter = adapterIngredients
        binding.rvPrepareMode.adapter = adapterPrepareMode
    }

    private fun showDialogNewIngredient() {
        typeInsert = "INGREDIENT"
        DialogEditTextFragment.show(
            title = getString(R.string.label_new_ingredient),
            placeholder = getString(R.string.label_item_description),
            fragmentManager = parentFragmentManager
        )
    }

    private fun showDialogNewPrepareMode() {
        typeInsert = "PREPARE_MODE"
        DialogEditTextFragment.show(
            title = getString(R.string.label_new_prepare_mode),
            placeholder = getString(R.string.label_item_description),
            fragmentManager = parentFragmentManager
        )
    }
}