package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class WholesaleProductStatisticUpdateModel(
    val WholesaleProductStatisticId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val IpAddress: String = "",
    val SecureKey: String = ""
)
