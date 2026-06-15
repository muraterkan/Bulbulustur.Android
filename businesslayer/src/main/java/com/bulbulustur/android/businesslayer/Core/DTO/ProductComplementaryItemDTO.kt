package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductComplementaryItemDTO(
    val ComplementaryItemId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductSecureKey: String = "",
    val OtherProductSecureKey: String = ""
)
