package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleProductStatisticInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val IpAddress: String = "",
    val SecureKey: String = ""
)
