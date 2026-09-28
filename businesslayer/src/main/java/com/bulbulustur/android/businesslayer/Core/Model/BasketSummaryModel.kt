package com.bulbulustur.android.businesslayer.Core.Model

import com.bulbulustur.android.businesslayer.Core.DTO.GatewayStoreShippingBreakdownResponse

data class BasketSummaryModel(
    val SubTotal: Double = 0.0,
    val ShippingTotal: Double = 0.0,
    val Vat: Double = 0.0,
    val GrandTotal: Double = 0.0,
    val NetTotal: Double = 0.0,
    val StoreShippingBreakdown: List<GatewayStoreShippingBreakdownResponse> = emptyList()
)
