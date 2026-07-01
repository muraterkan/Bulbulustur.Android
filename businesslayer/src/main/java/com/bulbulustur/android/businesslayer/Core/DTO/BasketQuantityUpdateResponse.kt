package com.bulbulustur.android.businesslayer.Core.DTO

data class BasketQuantityUpdateResponse(
    val Success: Boolean = false,
    val Removed: Boolean = false,
    val BasketId: Int = 0,
    val Quantity: Int = 0,
    val LineTotal: Double = 0.0,
    val CurrencySymbol: String = "",
    val ItemCount: Int = 0,
    val TotalQuantity: Int = 0,
    val Summary: BasketSummaryDTO = BasketSummaryDTO()
)