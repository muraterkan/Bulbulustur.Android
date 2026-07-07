package com.bulbulustur.android.businesslayer.Core.DTO

data class DealsOfTheDayDTO(
    val DealsOfTheDayId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val CampaignPrice: Double = 0.0,
    val StoreId: Int = 0,
    val VariantId: Int = 0,
    val StartingDate: String = "",
    val EndingDate: String = "",
    val IsApproved: Boolean = false,
    val ProductName: String = "",
    val CategoryName: String = "",
    val Brand: String = "",
    val CurrencySymbol: String = "",
    val Picture: String = "",
    val Price: Double = 0.0,
    val Store: String = "",
    val Stock: Int = 0,
    val CategoryId: Int = 0,
    val Rating: Double = 0.0,
    val ReviewNumber: Int = 0,
    val Link: String = "",
    val DefaultPicture: String = "",
    val ProductVariantPriceId: Int = 0
)
