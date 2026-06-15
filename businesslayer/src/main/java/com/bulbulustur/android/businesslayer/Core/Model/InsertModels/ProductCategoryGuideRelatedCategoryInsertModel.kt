package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductCategoryGuideRelatedCategoryInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductCategoryGuideId: Int = 0,
    val RelatedProductCategoryId: Int = 0,
    val Sort: Int = 0
)
