package com.example.shoppingexample

import com.example.shoppingexample.flow.main.view.MainScreenUiState
import com.example.shoppingexample.flow.main.viewmodel.MainViewModel
import com.example.shoppingexample.model.ShoppingItemInfo
import com.example.shoppingexample.model.ShoppingListInfo
import com.example.shoppingexample.repository.ApiRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import java.net.HttpURLConnection

class MainViewModelTest {

    lateinit var mMainViewModel: MainViewModel
    @MockK
    lateinit var fakeRepo: ApiRepository

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        // 使用MockKAnnotations.init初始化MockK annotation的宣告
        // fakeRepo = mockk()
        MockKAnnotations.init(this)

        mMainViewModel = MainViewModel(fakeRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getShopListInfo() = runTest {
        // Arrange
        coEvery { fakeRepo.getShopListInfo() } returns Response.success(ShoppingListInfo(listOf(
            ShoppingItemInfo(
                price = 100,
                martName = "Test Store",
                martShortName = "Test Store",
                imageUrl = "",
                finalPrice = 100,
                stockAvailable = 100,
                martId = 100

            )
        )))

        // Action

        mMainViewModel.getShopListInfo("")
        advanceUntilIdle() // 等待 coroutine 結束

        // Assertion
        val state: MainScreenUiState.GetShoppingListState = mMainViewModel.uiState.value as MainScreenUiState.GetShoppingListState
        assertTrue(state.isSuccess)
        assertTrue(state.shoppingList.isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getShopListInfoFail() = runTest {
        // Arrange
        coEvery { fakeRepo.getShopListInfo() } returns Response.error(HttpURLConnection.HTTP_BAD_REQUEST, okhttp3.ResponseBody.create(null, ""))

        // Action

        mMainViewModel.getShopListInfo("")
        advanceUntilIdle() // 等待 coroutine 結束

        // Assertion
        val state: MainScreenUiState.GetShoppingListState = mMainViewModel.uiState.value as MainScreenUiState.GetShoppingListState
        assertFalse(state.isSuccess)
        assertTrue(state.shoppingList.isEmpty())
    }

}