package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductLowPriceReportUpdateModel(
    val ProductLowPriceReportId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductKey: Int = 0,
    val VariantId: Int = 0,
    val MemberKey: Int? = null,
    val CompetitorName: String = "",
    val CompetitorUrl: String = "",
    val CompetitorPrice: Double? = null,
    val Cancellation: Boolean = false,
    val CancellationDate: String? = null
)
