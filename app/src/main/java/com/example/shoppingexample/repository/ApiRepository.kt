package com.example.shoppingexample.repository

import com.example.shoppingexample.model.ShoppingListInfo
import com.example.shoppingexample.net.api.IApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiRepository(private val mApi: IApi) {
    suspend fun getShopListInfo():Response<ShoppingListInfo?> = withContext(Dispatchers.IO) {
        return@withContext mApi.getShoppingListInfo()
    }
}