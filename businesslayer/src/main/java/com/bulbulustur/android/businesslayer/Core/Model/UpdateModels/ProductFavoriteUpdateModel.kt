package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductFavoriteUpdateModel(
    val ProductFavoriteId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Note: String = "",
    val StoreId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val CurrencyId: Int? = null
)
