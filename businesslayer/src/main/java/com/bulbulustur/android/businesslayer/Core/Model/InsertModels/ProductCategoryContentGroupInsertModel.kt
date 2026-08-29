package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductCategoryContentGroupInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductCategoryId: Int = 0,
    val GroupName: String = "",
    val Sort: Int = 0,
    val Picture: String = "",
    val BackgroundColor: String = "",
    val BackgroundPicture: String = "",
    val TextColor: String = ""
)
