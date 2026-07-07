package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class DealsOfTheDayInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val CampaignPrice: Double = 0.0,
    val StoreId: Int = 0,
    val VariantId: Int = 0,
    val StartingDate: String = "",
    val EndingDate: String = "",
    val IsApproved: Boolean = false
)
