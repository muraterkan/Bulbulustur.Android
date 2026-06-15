package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleMessageInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MessageThreadId: Int = 0,
    val Body: String = "",
    val First: Boolean = false
)
