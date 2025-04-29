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
            var currentIndex = 0

            while (currentIndex < text.length) {
                val foundIndex = text.indexOf(keyword, currentIndex, true)

                if (foundIndex == -1 || keyword.isEmpty()) {
                    // 無關鍵字存在, 附加原始字並結束建構
                    append(text.substring(currentIndex))
                    break
                }

                // 先加關鍵字前的字
                if (foundIndex > currentIndex) {
                    append(text.substring(currentIndex, foundIndex))
                }

                // 加高亮字
                withStyle(SpanStyle(color = color)) {
                    append(text.substring(foundIndex, foundIndex + keyword.length))
                }

                currentIndex = foundIndex + keyword.length
            }
        },
        style = style,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}