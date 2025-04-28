package com.example.shoppingexample.flow.main.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingexample.extension.noNullValue
import com.example.shoppingexample.flow.main.view.MainScreenUiState
import com.example.shoppingexample.model.ShoppingItemInfo
import com.example.shoppingexample.repository.ApiRepository
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("StaticFieldLeak")
class MainViewModel(
    private val mApiRepository: ApiRepository
): ViewModel() {

    private var mShoppingList: List<ShoppingItemInfo> = emptyList()

    private var mUiState: MutableState<MainScreenUiState> = mutableStateOf(MainScreenUiState.GetShoppingListState())
    var uiState: State<MainScreenUiState> = mUiState

    fun getShopListInfo(keyword: String) {
        viewModelScope.launch {
            if(keyword.isEmpty() && mShoppingList.isEmpty()) {
                try {
                    val response = mApiRepository.getShopListInfo()

                    if (response.isSuccessful) {
                        mShoppingList = response.body()?.data ?: emptyList()
                        mUiState.value = MainScreenUiState.GetShoppingListState(shoppingList = mShoppingList)
                    } else {
                        mUiState.value = MainScreenUiState.GetShoppingListState(isSuccess = false, msg =  response.message())
                    }
                } catch (e: Exception) {
                    mUiState.value = MainScreenUiState.GetShoppingListState(isSuccess = false, msg =  e.message.noNullValue)
                }
            } else {
                val filteredShoppingList = mShoppingList.filter { it.martName?.lowercase()?.contains(keyword.lowercase()) ?: false }
                mUiState.value = MainScreenUiState.GetShoppingListState(shoppingList = filteredShoppingList)
            }
        }
    }
}