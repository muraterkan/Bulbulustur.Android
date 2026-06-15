package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductBuyTogetherInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val OtherProductId: Int = 0
)
