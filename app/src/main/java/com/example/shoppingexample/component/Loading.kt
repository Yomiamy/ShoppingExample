package com.example.shoppingexample.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppingexample.ui.theme.Color_008FDF

@Composable
fun Loading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.size(100.dp),
        strokeWidth = 8.dp,
        color = Color_008FDF
    )
}