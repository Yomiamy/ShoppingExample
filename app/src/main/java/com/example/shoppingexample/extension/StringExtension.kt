package com.example.shoppingexample.extension

val String?.noNullValue: String
    get() = this ?: ""