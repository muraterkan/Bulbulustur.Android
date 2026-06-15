package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class WholesaleBuyerSampleRequestUpdateModel(
    val WholesaleBuyerSampleRequestId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val WholesaleProductId: Int = 0,
    val ProductName: String = "",
    val Description: String = "",
    val UnitId: Int = 0,
    val SamplePrice: Double = 0.0,
    val CurrencyId: Int = 0,
    val Quantity: Int = 0
)
