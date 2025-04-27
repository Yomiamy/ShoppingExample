package com.example.shoppingexample.net.api

import com.example.shoppingexample.model.ShoppingListInfo
import retrofit2.Response
import retrofit2.http.*

interface IApi {

    /**
     * Get shopping list
     */
    @GET("/apis2/test/marttest.jsp")
    suspend fun getShoppingListInfo(): Response<ShoppingListInfo?>
}