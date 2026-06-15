package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductVariantPriceHistoryUpdateModel(
    val ProductVariantPriceHistoryId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val StoreId: Int = 0,
    val ProductId: Int = 0,
    val ProductSecureKey: String = "",
    val VariantId: Int = 0,
    val VariantSecureKey: String = "",
    val Price: Double = 0.0,
    val CurrencyId: Int = 0
)
