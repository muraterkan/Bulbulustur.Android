package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductComplementaryItemUpdateModel(
    val ComplementaryItemId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductSecureKey: String = "",
    val OtherProductSecureKey: String = ""
)
