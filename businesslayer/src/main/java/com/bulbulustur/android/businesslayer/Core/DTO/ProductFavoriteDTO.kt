package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductFavoriteDTO(
    val FavoriteId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val FavoriteTypeId: Int = 0,
    val MemberId: Int = 0,
    val ProductId: Int = 0,
    val Note: String = "",
    val StoreId: Int = 0,
    val VariantId: Int = 0,
    val MemberName: String = "",
    val ProductName: String = "",
    val Picture: String = "",
    val Price: Double = 0.0,
    val CurrencyId: Int = 0,
    val CurrencySymbol: String = "",
    val ColorId: Int = 0,
    val DefaultPicture: String = ""
)