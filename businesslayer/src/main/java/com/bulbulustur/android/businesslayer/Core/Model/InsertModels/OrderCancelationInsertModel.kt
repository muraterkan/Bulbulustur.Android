package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class OrderCancelationInsertModel(
    val InsertedBy: Int = 0,
    val OrderStoreLineId: Int = 0,
    val OrderCancelationTypeId: Int = 0,
    val OrderKey: String = "",
    val Description: String = ""
)