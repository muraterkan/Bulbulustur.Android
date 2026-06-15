package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductPropertyValueDTO(
    val PropertyValueId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val PropertyCategoryId: Int = 0,
    val PropertyValue: String = ""
)
