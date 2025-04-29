package com.example.shoppingexample.flow.main.view

import com.example.shoppingexample.model.ShoppingItemInfo

sealed class MainScreenUiState {
    object Loading: MainScreenUiState()

    data class GetShoppingListState(
        val isSuccess: Boolean = true,
        val msg: String = "Error!!",
        val shoppingList: List<ShoppingItemInfo> = emptyList()
    ) : MainScreenUiState()
}