package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductCategoryGuideCardInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CategoryGuideId: Int = 0,
    val IconClass: String = "",
    val Picture: String = "",
    val ButtonLink: String = "",
    val Sort: Int = 0
)
