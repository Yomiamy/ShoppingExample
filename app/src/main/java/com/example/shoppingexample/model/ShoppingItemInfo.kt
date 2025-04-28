package com.example.shoppingexample.model

import com.example.shoppingexample.extension.formattedPrice
import com.google.gson.annotations.SerializedName

data class ShoppingItemInfo(@SerializedName("price")
                            val price: Int?,
                            @SerializedName("martShortName")
                            val martShortName: String?,
                            @SerializedName("imageUrl")
                            val imageUrl: String?,
                            @SerializedName("finalPrice")
                            val finalPrice: Int?,
                            @SerializedName("martName")
                            val martName: String?,
                            @SerializedName("stockAvailable")
                            val stockAvailable: Int?,
                            @SerializedName("martId")
                            val martId: Int?){

    val finalPriceDispStr:String
        get() = "$${finalPrice?.formattedPrice()}"

    val martNameDispStr:String
        get() = "${martName ?: 0}"

    val martIdDispStr:String
        get() = "${martId ?: 0}"

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is ShoppingItemInfo) {
            return false
        }

        return price == other.price
                && martShortName.equals(other.martShortName)
                && imageUrl.equals(other.imageUrl)
                && finalPrice == other.finalPrice
                && martName.equals(other.martName)
                && stockAvailable == other.stockAvailable
                && martId == other.martId
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
