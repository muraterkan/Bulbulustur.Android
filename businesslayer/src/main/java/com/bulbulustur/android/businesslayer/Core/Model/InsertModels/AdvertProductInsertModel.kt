package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class AdvertProductInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val AdvertId: Int = 0,
    val AdvertKey: String = "",
    val CompanyId: Int = 0,
    val StoreId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val Content: String = "",
    val Click: Int = 0,
    val Impression: Int = 0
)
