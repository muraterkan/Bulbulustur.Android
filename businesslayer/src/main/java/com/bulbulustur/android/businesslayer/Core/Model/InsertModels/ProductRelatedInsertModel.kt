package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductRelatedInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val RelatedProductId: Int = 0
)
