package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductBrowsingHistoryInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val MemberId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val StoreId: Int = 0
)