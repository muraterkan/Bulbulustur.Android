package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class WholesaleMessageUpdateModel(
    val WholesaleMessageId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MessageThreadId: Int = 0,
    val Body: String = "",
    val First: Boolean = false
)
