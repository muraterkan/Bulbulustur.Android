package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class AdvertSponsoredInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val StoreId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val ProductCategoryId: Int = 0,
    val Click: Int = 0,
    val Impression: Int = 0
)
