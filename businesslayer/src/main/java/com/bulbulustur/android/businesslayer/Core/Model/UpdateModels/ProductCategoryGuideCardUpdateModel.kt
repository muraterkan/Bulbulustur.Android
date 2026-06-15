package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductCategoryGuideCardUpdateModel(
    val ProductCategoryGuideCardId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CategoryGuideId: Int = 0,
    val IconClass: String = "",
    val Picture: String = "",
    val ButtonLink: String = "",
    val Sort: Int = 0
)
