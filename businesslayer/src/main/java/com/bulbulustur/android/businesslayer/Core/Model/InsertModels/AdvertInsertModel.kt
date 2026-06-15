package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class AdvertInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductCategoryId: Int = 0,
    val CompanyId: Int = 0,
    val AdvertTypeId: Int = 0,
    val AdvertKey: String = "",
    val AdvertStatusId: Int = 0,
    val Click: Int = 0,
    val Impression: Int = 0
)
