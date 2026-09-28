package com.bulbulustur.android.businesslayer.Core.DTO

import com.bulbulustur.android.businesslayer.Core.Model.BasketSummaryModel

data class CheckoutBasketSnapshotDTO(
    val Items: List<BasketDTO> = emptyList(),
    val Totals: BasketSummaryModel = BasketSummaryModel()
)
