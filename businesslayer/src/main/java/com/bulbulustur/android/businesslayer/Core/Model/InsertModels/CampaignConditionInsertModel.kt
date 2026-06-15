package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class CampaignConditionInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CampaignId: Int = 0,
    val Content: String = "",
    val Description: String = ""
)
