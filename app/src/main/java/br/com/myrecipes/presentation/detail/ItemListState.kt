package br.com.myrecipes.presentation.detail

import br.com.myrecipes.presentation.model.ItemListModel

interface ItemListState {
    object Loading : ItemListState
    data class Success(
        val ingredients: List<ItemListModel>,
        val prepareMode: List<ItemListModel>
    ) : ItemListState
    data class Error(val message: String) : ItemListState
}