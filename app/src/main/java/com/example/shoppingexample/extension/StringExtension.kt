package com.example.shoppingexample.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.shoppingexample.ui.theme.Color_008FDF

val String?.noNullValue: String
    get() = this ?: ""


fun String.buildHighlightedString(
    keyword: String,
    highlightedColor: Color = Color_008FDF
): AnnotatedString {
    return buildAnnotatedString {
        var currentIndex = 0

        while (currentIndex < this@buildHighlightedString.length) {
            val foundIndex = this@buildHighlightedString.indexOf(keyword, currentIndex, true)

            if (foundIndex == -1 || keyword.isEmpty()) {
                // 無關鍵字存在, 附加原始字並結束建構
                append(this@buildHighlightedString.substring(currentIndex))
                break
            }

            // 先加關鍵字前的字
            if (foundIndex > currentIndex) {
                append(this@buildHighlightedString.substring(currentIndex, foundIndex))
            }

            // 加高亮字
            withStyle(SpanStyle(color = highlightedColor)) {
                append(this@buildHighlightedString.substring(foundIndex, foundIndex + keyword.length))
            }

            currentIndex = foundIndex + keyword.length
        }
    }
}