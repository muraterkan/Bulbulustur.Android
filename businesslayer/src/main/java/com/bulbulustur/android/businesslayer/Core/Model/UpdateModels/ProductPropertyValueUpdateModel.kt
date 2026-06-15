package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductPropertyValueUpdateModel(
    val PropertyValueId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val PropertyCategoryId: Int = 0,
    val PropertyValue: String = ""
)
