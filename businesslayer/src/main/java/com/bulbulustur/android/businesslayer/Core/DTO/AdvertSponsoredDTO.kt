package com.bulbulustur.android.businesslayer.Core.DTO

data class AdvertSponsoredDTO(
    val AdvertSponsoredId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val CompanyId: Int = 0,
    val StoreId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val ProductVariantPriceId: Int = 0,
    val ProductCategoryId: Int = 0,

    val ProductName: String = "",
    val DefaultPicture: String = "",
    val Price: Double = 0.0,
    val Unit: String = "",
    val CurrencySymbol: String = "",

    val Click: Int = 0,
    val Impression: Int = 0
)