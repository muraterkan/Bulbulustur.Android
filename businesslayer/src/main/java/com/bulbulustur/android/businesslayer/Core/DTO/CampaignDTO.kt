package com.bulbulustur.android.businesslayer.Core.DTO

data class CampaignDTO(
    val CampaignId: Int = 0,
    val InsertedBy: Int = 0,
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
    val CampaignCondition: String = "",
    val CategoryName: String = "",
    val DefaultPicture: String = "",
    val CampaignProducts: List<CampaignProductDTO> = emptyList()
)
