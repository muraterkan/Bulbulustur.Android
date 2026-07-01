package com.bulbulustur.android.businesslayer.Core.DTO

data class BasketSummaryDTO(
    val NetTotal: Double = 0.0,
    val VatTotal: Double = 0.0,
    val ShippingCost: Double = 0.0,
    val GrossTotal: Double = 0.0
)