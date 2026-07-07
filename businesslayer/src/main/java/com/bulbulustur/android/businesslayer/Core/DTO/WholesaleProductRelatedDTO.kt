package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleProductRelatedDTO(
    val WholesaleProductRelatedId: Int = 0,
    val WholesaleProductId: Int = 0,
    val RelatedWholesaleProductId: Int = 0,
    val ProductName: String = "",
    val CategoryName: String = "",
    val Price: Double = 0.0,
    val CurrencySymbol: String = "",
    val MinimumOrderQuantity: Int = 0,
    val DefaultPicture: String = "",
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val SecureKey: String = ""
)
