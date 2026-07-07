package com.bulbulustur.android.businesslayer.Core.DTO

data class CampaignProductDTO(
    val CampaignProductId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val StoreId: Int = 0,
    val CampaignPrice: Double = 0.0,
    val CampaignId: Int = 0,
    val ProductName: String = "",
    val StoreName: String = "",
    val Color: String = "",
    val Size: String = "",
    val Price: Double = 0.0,
    val DefaultPicture: String = "",
    val CategoryName: String = "",
    val ProductSecureKey: String = "",
    val ProductVariantPriceId: Int = 0
)
