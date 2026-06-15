package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleProductRelatedInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val WholesaleProductId: Int = 0,
    val RelatedWholesaleProductId: Int = 0,
    val SecureKey: String = ""
)
