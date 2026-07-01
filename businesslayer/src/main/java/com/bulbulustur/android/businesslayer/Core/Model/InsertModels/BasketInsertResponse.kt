package com.bulbulustur.android.businesslayer.Core.DTO

data class BasketInsertResponse(
    val Success: Boolean = false,
    val Message: String = "",
    val ItemCount: Int = 0,
    val TotalQuantity: Int = 0
)