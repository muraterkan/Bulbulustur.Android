package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class CampaignProductInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val StoreId: Int = 0,
    val CampaignPrice: Double = 0.0,
    val CampaignId: Int = 0
)
