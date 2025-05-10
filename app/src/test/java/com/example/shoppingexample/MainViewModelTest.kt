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


}