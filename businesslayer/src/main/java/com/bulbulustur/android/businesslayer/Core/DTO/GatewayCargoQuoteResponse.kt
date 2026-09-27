package com.bulbulustur.android.businesslayer.Core.DTO

data class GatewayCargoQuoteResponse(
    val Success: Boolean = false,
    val MemberId: Int = 0,
    val NetTotal: Double = 0.0,
    val Vat: Double = 0.0,
    val SubTotal: Double = 0.0,
    val ShippingTotal: Double = 0.0,
    val GrandTotal: Double = 0.0,
    val FinalDesi: Double = 0.0,
    val StoreShippingBreakdown: List<GatewayStoreShippingBreakdownResponse> = emptyList(),
    val ErrorCode: String? = null,
    val Message: String? = null
)

data class GatewayStoreShippingBreakdownResponse(
    val StoreId: Int = 0,
    val StoreDesi: Int = 0,
    val StoreShippingBasePrice: Double = 0.0,
    val StoreShippingAfterDiscount: Double = 0.0
)