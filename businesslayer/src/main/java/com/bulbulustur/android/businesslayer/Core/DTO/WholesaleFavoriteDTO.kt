package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleFavoriteDTO(
    val WholesaleFavoriteId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val WholesaleProductId: Int = 0,
    val Note: String = "",
    val MemberName: String = "",
    val ProductName: String = "",
    val Picture: String = "",
    val Price: Double = 0.0,
    val CurrencyId: Int = 0,
    val CurrencySymbol: String = "",
    val ColorId: Int = 0,
    val DefaultPicture: String = ""
)