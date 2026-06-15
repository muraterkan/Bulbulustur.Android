package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductPropertyValueInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val PropertyCategoryId: Int = 0,
    val PropertyValue: String = ""
)
