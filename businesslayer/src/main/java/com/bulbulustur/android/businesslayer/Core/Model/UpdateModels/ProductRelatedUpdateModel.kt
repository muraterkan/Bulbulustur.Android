package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductRelatedUpdateModel(
    val ProductRelatedId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val RelatedProductId: Int = 0
)
