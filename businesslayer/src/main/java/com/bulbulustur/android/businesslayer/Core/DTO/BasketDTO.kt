package com.bulbulustur.android.businesslayer.Core.DTO

data class BasketDTO(
    val BasketId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val BasketKey: String = "",
    val Quantity: Int = 0,
    val UnitId: Int = 0,
    val UnitPrice: Double = 0.0,
    val CurrencyId: Int = 0,
    val TotalPrice: Double = 0.0,
    val VatAmount: Double = 0.0,
    val VatRateId: Int = 0,
    val DiscountAmount: Double = 0.0,
    val StoreId: Int = 0,
    val ProductCategoryId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val Barcode: String = "",
    val GiftNote: String = "",
    val Text1: String = "",
    val Text2: String = "",
    val CargoDesiId: Int = 0,
    val StoreBoxRuleId: Int = 0
)
