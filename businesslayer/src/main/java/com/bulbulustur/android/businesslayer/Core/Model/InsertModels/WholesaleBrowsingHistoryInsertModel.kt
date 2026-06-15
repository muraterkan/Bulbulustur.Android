package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleBrowsingHistoryInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val WholesaleProductId: Int = 0,
    val MemberId: Int = 0
)
