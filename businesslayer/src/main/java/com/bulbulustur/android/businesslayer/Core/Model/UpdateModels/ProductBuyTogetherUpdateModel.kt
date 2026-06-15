package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductBuyTogetherUpdateModel(
    val ProductBuyTogetherId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val OtherProductId: Int = 0
)
