package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductCustomerQuestionInsertModel(
    val StoreId: Int = 0,
    val ProductSecureKey: String = "",
    val ProductId: Int = 0,
    val Question: String = ""
)