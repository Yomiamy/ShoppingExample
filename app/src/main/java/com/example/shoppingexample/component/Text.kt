package com.example.shoppingexample.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.example.shoppingexample.ui.theme.Color_008FDF

@Composable
fun HighlightedText(
    modifier: Modifier,
    text: String,
    keyword: String,
    style: TextStyle,
    color: Color = Color_008FDF
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            if (keyword.isEmpty()) {
                append(text)
            } else {
                val parts = text.split(keyword)
                for (i in parts.indices) {
                    append(parts[i])
                    if (i != parts.size - 1) {
                        withStyle(SpanStyle(color = color)) {
                            append(keyword)
                        }
                    }
                }
            }
        },
        style = style,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}