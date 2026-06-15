package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleBuyerCustomizeRequestInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val WholesaleProductId: Int = 0,
    val ProductName: String = "",
    val Description: String = "",
    val SendOtherSeller: Int = 0
)
