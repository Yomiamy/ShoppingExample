package com.example.shoppingexample.flow.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shoppingexample.component.Loading
import com.example.shoppingexample.extension.buildHighlightedString
import com.example.shoppingexample.extension.noNullValue
import com.example.shoppingexample.flow.detail.NAV_DETAIL_ROUTE
import com.example.shoppingexample.flow.main.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.shoppingexample.model.ShoppingItemInfo
import com.example.shoppingexample.ui.theme.Color_000000
import com.example.shoppingexample.ui.theme.Color_008FDF
import com.example.shoppingexample.ui.theme.Color_C0C0C0
import com.example.shoppingexample.ui.theme.Color_C25782
import com.example.shoppingexample.ui.theme.Color_FFFFFF

const val NAV_MAIN_ROUTE = "/main"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var input by remember { mutableStateOf("") }
    val mainViewModel: MainViewModel = koinViewModel()
    val uiState = mainViewModel.uiState.value

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        SearchBarLayout(
                            Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth(),
                            input,
                            {
                                input = it
                                mainViewModel.getShopListInfo(it)
                            }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        // 設置背景顏色
                        containerColor = Color_008FDF
                    )
                )
            }
        ) { innerPadding ->
            if(uiState is MainScreenUiState.GetShoppingListState) {
                if(uiState.isSuccess) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color_C0C0C0)
                    ) {
                        items(uiState.shoppingList) { shopItemInfo ->
                            ShopItemLayout(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp, start = 5.dp, end = 5.dp)
                                    .background(Color_FFFFFF, shape = RoundedCornerShape(5.dp)),
                                input,
                                shopItemInfo
                            ) {
                                navController.navigate(
                                    "$NAV_DETAIL_ROUTE?martId=${shopItemInfo.martId}&martName=${shopItemInfo.martName}&price=${shopItemInfo.price}&imageUrl=${shopItemInfo.imageUrl}"
                                )
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Text(
                            uiState.msg,
                            modifier = Modifier.padding(10.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color_000000,
                                fontSize = 20.sp
                            )
                        )
                    }
                }
            } else if(uiState is MainScreenUiState.Loading) {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(), // 填滿螢幕
                    contentAlignment = Alignment.Center // 重點：置中
                ) {
                    Loading()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        mainViewModel.getShopListInfo(input)
    }
}


@Composable
fun SearchBarLayout(modifier: Modifier, input: String, onSearchTextChanged: (String) -> Unit) {
    Row(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(size = 30.dp),
                color = Color_FFFFFF
            )
            .padding(horizontal = 5.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(android.R.drawable.ic_menu_search),
            contentDescription = ""
        )

        BasicTextField(
            value = input,
            singleLine = true,
            textStyle = TextStyle(fontSize = 18.sp),
            onValueChange = onSearchTextChanged
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ShopItemLayout(
    modifier: Modifier,
    keyword: String,
    shoppingItemInfo: ShoppingItemInfo,
    onItemClick: () -> Unit
) {
    ConstraintLayout(modifier.clickable {
        onItemClick()
    }) {
        val (image, martNameTxt, finalPriceTxt, funcLayout) = createRefs()

        GlideImage(
            model = shoppingItemInfo.imageUrl.noNullValue,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                .size(120.dp, 100.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )

        Text(shoppingItemInfo.martNameDispStr.buildHighlightedString(keyword),
            modifier = Modifier
                .padding(top = 10.dp, start = 15.dp, end = 10.dp)
                .constrainAs(martNameTxt) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color_000000),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis)

        Text(
            shoppingItemInfo.finalPriceDispStr.buildHighlightedString(keyword),
            modifier = Modifier
                .padding(top = 10.dp, start = 15.dp)
                .constrainAs(finalPriceTxt) {
                    top.linkTo(martNameTxt.bottom)
                    start.linkTo(martNameTxt.start)
                    end.linkTo(martNameTxt.end)
                    width = Dimension.fillToConstraints
                },
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color_C25782))

        Row(modifier = Modifier
            .padding(bottom = 10.dp, end = 10.dp)
            .constrainAs(funcLayout) {
                end.linkTo(martNameTxt.end)
                bottom.linkTo(image.bottom)
            }) {
            // Favorite
            Image(
                painter = painterResource(android.R.drawable.ic_input_add),
                contentDescription = ""
            )

            // ShopCart
            Image(
                painter = painterResource(android.R.drawable.btn_star_big_on),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val fakeNavController = rememberNavController()
    MainScreen(fakeNavController)
}