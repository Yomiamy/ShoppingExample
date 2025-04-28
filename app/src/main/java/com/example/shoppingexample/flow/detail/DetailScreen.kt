package com.example.shoppingexample.flow.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shoppingexample.R
import com.example.shoppingexample.extension.noNullValue
import com.example.shoppingexample.ui.theme.Color_000000
import com.example.shoppingexample.ui.theme.Color_008FDF
import com.example.shoppingexample.ui.theme.Color_C0C0C0
import com.example.shoppingexample.ui.theme.Color_C25782
import com.example.shoppingexample.ui.theme.Color_FFFFFF

const val NAV_DETAIL_ROUTE = "/detail"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    martId: Int,
    martName: String,
    priceDispText: String,
    imageUrl: String
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            stringResource(R.string.shopping_detail_toolbar_title),
                            style = TextStyle(
                                color = Color_FFFFFF,
                                fontSize = 22.sp
                            )
                        )
                    },
                    navigationIcon = {
                       Image(
                           modifier = Modifier
                               .padding(start = 20.dp)
                               .size(30.dp)
                               .clickable {
                                   navController.navigateUp()
                               },
                           painter = painterResource(R.drawable.ic_back),
                           contentDescription = ""
                       )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        // 設置背景顏色
                        containerColor = Color_008FDF
                    )
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color_FFFFFF)) {
                Column {
                    GlideImage(
                        model = imageUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Inside,
                        contentDescription = ""
                    )

                    // 產品編號
                    Row {
                        Text(
                            stringResource(R.string.shopping_detail_mart_id, martId),
                            modifier = Modifier.padding(top = 15.dp, start = 15.dp),
                            style = TextStyle(
                                color = Color_C0C0C0,
                                fontSize = 16.sp
                            )
                        )
                    }

                    // 產品名稱
                    Text(
                        martName,
                        modifier = Modifier.padding(top = 15.dp, start = 15.dp, end = 15.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color_000000,
                            fontSize = 22.sp
                        )
                    )

                    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                        val (priceText, funcLayout) = createRefs()

                        // 產品價格
                        Text(
                            priceDispText,
                            modifier = Modifier.padding(top = 15.dp, start = 15.dp).constrainAs(priceText) {
                                start.linkTo(parent.start)
                            },
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color_C25782,
                                fontSize = 22.sp
                            )
                        )

                        // 功能按鈕
                        Row(modifier = Modifier
                            .padding(top = 15.dp, end = 10.dp)
                            .constrainAs(funcLayout) {
                                top.linkTo(priceText.top)
                                end.linkTo(parent.end)
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val mockNavController = rememberNavController()
    val mockMartId = 1255212
    val mockMartName = "iPhone 12 Pro Max 256GB"
    val mockPrice = "$39,950"
    val mockImageUrl= "https://pdinfo.senao.com.tw/octopus/contents/99b404a6bcfb4a74a27e4a10746fb258.jpg"
    DetailScreen(mockNavController, mockMartId, mockMartName, mockPrice, mockImageUrl)
}