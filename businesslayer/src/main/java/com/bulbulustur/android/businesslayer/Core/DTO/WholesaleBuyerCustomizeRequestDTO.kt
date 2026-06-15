package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleBuyerCustomizeRequestDTO(
    val WholesaleBuyerCustomizeRequestId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val WholesaleProductId: Int = 0,
    val ProductName: String = "",
    val Description: String = "",
    val SendOtherSeller: Int = 0
)
