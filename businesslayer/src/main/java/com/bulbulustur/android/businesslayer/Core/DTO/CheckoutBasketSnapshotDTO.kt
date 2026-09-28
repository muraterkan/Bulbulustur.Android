package com.bulbulustur.android.businesslayer.Core.DTO

data class CheckoutBasketSnapshotDTO(
    val Items: List<BasketDTO> = emptyList(),
    val Totals: BasketSummaryDTO = BasketSummaryDTO()
)
