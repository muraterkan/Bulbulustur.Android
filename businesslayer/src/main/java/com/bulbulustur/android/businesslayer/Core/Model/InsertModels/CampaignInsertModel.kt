package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class CampaignInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CampaignName: String = "",
    val CampaignConditionId: Int = 0,
    val Description: String = "",
    val CampaignStartDate: String = "",
    val CampaignEndDate: String = "",
    val Picture: String = "",
    val MaximumProducts: Int = 0,
    val ProductCategoryId: Int = 0,
    val SeoTitle: String = "",
    val SeoDescription: String = ""
)
