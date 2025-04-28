package com.example.shoppingexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shoppingexample.extension.noNullValue
import com.example.shoppingexample.flow.detail.DetailScreen
import com.example.shoppingexample.flow.main.view.NAV_MAIN_ROUTE
import com.example.shoppingexample.flow.detail.NAV_DETAIL_ROUTE
import com.example.shoppingexample.flow.main.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavHostMain()
        }
    }
}

@Composable
fun NavHostMain() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NAV_MAIN_ROUTE) {
        composable(NAV_MAIN_ROUTE) {
            MainScreen(navController)
        }

        composable(
            route = "$NAV_DETAIL_ROUTE?martId={martId}&martName={martName}&priceDispText={priceDispText}&imageUrl={imageUrl}",
            arguments = listOf(
                navArgument(name = "martId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(name = "martName") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "priceDispText") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "imageUrl") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val martId = it.arguments?.getInt("martId").noNullValue
            val martName = it.arguments?.getString("martName").noNullValue
            val priceDispText = it.arguments?.getString("priceDispText").noNullValue
            val imageUrl= it.arguments?.getString("imageUrl").noNullValue
            DetailScreen(navController, martId, martName, priceDispText, imageUrl)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NavHostMainPreview() {
    NavHostMain()
}