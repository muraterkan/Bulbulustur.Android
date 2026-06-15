package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductComplementaryItemInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductSecureKey: String = "",
    val OtherProductSecureKey: String = ""
)
