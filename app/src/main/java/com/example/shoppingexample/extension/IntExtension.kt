package com.example.shoppingexample.extension

import java.util.Locale

fun Int?.formattedPrice() = String.format(Locale.getDefault(), "%,d", this ?: 0)

val Int?.noNullValue: Int
    get() = this ?: 0