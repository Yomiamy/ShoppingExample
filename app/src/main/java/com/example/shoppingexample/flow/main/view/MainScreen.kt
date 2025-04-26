package com.example.shoppingexample.flow.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shoppingexample.extension.noNullValue
import com.example.shoppingexample.model.ShoppingItemInfo
import com.example.shoppingexample.model.ShoppingListInfo
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
    // TODO: Fake data
    val mockShopList = ShoppingListInfo(
        listOf(
            ShoppingItemInfo(
                price = 39950,
                martShortName = "iPhone 12 Pro Max 256GB",
                imageUrl = "https://pdinfo.senao.com.tw/octopus/contents/99b404a6bcfb4a74a27e4a10746fb258.jpg",
                finalPrice = 39950,
                martName = "iPhone 12 Pro Max 256GB【下殺97折 送保護貼兌換券】",
                stockAvailable = 30,
                martId = 1250797
            ),
            ShoppingItemInfo(
                price = 36091,
                martShortName = "iPhone 12 Pro 256GB",
                imageUrl = "https://pdinfo.senao.com.tw/octopus/contents/ee507a92f31346a2a3d28dd5a899ff36.jpg",
                finalPrice = 36091,
                martName = "iPhone 12 Pro 256GB【下殺97折 送保護貼兌換券】",
                stockAvailable = 17,
                martId = 1255212
            ),
            ShoppingItemInfo(
                price = 27930,
                martShortName = "iPhone 12 128GB",
                imageUrl = "https://pdinfo.senao.com.tw/octopus/contents/aa59e4bee3c24e57b2179d637b77919b.jpg",
                finalPrice = 27930,
                martName = "iPhone 12  128GB 紫色【新機預約 下殺98折 贈旅充】",
                stockAvailable = 0,
                martId = 1260924
            ),
            ShoppingItemInfo(
                price = 27503,
                martShortName = "iPhone 12 128GB",
                imageUrl = "https://pdinfo.senao.com.tw/octopus/contents/aa59e4bee3c24e57b2179d637b77919b.jpg",
                finalPrice = 27503,
                martName = "iPhone 12  128GB 紫色【新機預約 下殺97折 送保護貼兌換券】",
                stockAvailable = 0,
                martId = 1260800
            ),
            ShoppingItemInfo(
                price = 37142,
                martShortName = "iPhone 12 Pro Max 128GB",
                imageUrl = "https://pdinfo.senao.com.tw/octopus/contents/c10cf98092c04407944f5938013cc6e6.jpg",
                finalPrice = 37142,
                martName = "iPhone 12 Pro Max 128GB【下殺98折 送保護貼兌換券】",
                stockAvailable = 6,
                martId = 1248853
            ),
            ShoppingItemInfo(
                price = 33900,
                martShortName = "iPhone 12 Pro 128GB",
                imageUrl = "https://pdinfo.senao.com.tw/octopus/contents/838434c68e234109adb592edbd1f90d0.jpg",
                finalPrice = 33900,
                martName = "iPhone 12 Pro 128GB【送保護貼兌換券】",
                stockAvailable = 19,
                martId = 1244286
            )
        )
    )

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
                            { input = it }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        // 設置背景顏色
                        containerColor = Color_008FDF
                    )
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color_C0C0C0)
            ) {
                items(mockShopList.data.orEmpty()) { shopItemInfo ->
                    ShopItemLayout(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 5.dp, end = 5.dp)
                            .background(Color_FFFFFF, shape = RoundedCornerShape(5.dp)),
                        shopItemInfo
                    ) {
                        // TODO: 項目點擊
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        // TODO: 加載資料
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

        Text(shoppingItemInfo.martName.noNullValue,
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

        Text(shoppingItemInfo.finalPriceDispStr.noNullValue,
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