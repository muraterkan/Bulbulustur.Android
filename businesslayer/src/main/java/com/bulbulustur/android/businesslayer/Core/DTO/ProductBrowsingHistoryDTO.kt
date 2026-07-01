package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductBrowsingHistoryDTO(
    val BrowsingHistoryId: Int = 0,

    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val MemberId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val StoreId: Int = 0,

    val ProductName: String = "",
    val Picture: String = "",
    val DefaultPicture: String = "",

    val CategoryName: String = "",
    val CategoryId: Int? = null,

    val Price: Double = 0.0,
    val CurrencyId: Int = 0,
    val CurrencySymbol: String = "",

    val Product: String = "",
    val ColorId: Int = 0,

    val Rating: Double = 0.0,
    val ReviewNumber: Int = 0
)