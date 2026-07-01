package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductVariantDTO(
    val VariantId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val VariantSecureKey: String = "",

    val ProductId: Int = 0,
    val ProductSecureKey: String = "",
    val ProductCategoryId: Int = 0,
    val ProductName: String = "",
    val CategoryName: String = "",

    val ColorId: Int = 0,
    val Color: String = "",

    val SizeId: Int = 0,
    val Size: String = "",

    val Barcode: String = "",
    val CargoDesiId: Int = 0,

    val StoreId: Int = 0,
    val Store: String = "",
    val StoreLogo: String = "",
    val Rating: Double = 0.0,

    val ProductVariantPriceId: Int = 0,
    val Price: Double = 0.0,
    val CurrencyId: Int = 0,
    val CurrencySymbol: String = "",

    val Stock: Int = 0,
    val Stockcode: String = "",

    val DefaultPicture: String = "",
    val Picture: String = ""
)