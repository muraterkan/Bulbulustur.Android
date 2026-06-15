package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class RefundRequestUpdateModel(
    val RefundRequestId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val OrderId: Int = 0,
    val OrderLineId: Int = 0,
    val StoreId: Int = 0,
    val Email: String = "",
    val IsProcessed: Boolean = false,
    val ProcessedDate: String? = null
)
