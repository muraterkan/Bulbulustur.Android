package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class WholesaleBuyerLastPriceRequestUpdateModel(
    val WholesaleBuyerLastPriceRequestId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val WholesaleProductId: Int = 0,
    val ProductName: String = "",
    val Description: String = "",
    val CategoryId: Int = 0,
    val UnitPriceRequested: Double? = null,
    val CurrencyId: Int = 0,
    val ApprovalDate: String? = null,
    val ApprovalNote: String = "",
    val PaymentTypeId: Int = 0,
    val LastRequestDate: String = "",
    val EstimatedOrderQuantity: Double = 0.0,
    val UnitId: Int = 0,
    val CargoTarget: String = ""
)
