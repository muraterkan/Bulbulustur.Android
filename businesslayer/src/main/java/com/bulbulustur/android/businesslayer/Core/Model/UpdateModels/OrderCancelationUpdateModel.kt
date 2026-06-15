package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class OrderCancelationUpdateModel(
    val OrderCancelationId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val OrderKey: String = "",
    val OrderStoreLineId: Int = 0,
    val OrderCancelationTypeId: Int = 0,
    val Description: String = "",
    val OrderStoreId: Int? = null
)
