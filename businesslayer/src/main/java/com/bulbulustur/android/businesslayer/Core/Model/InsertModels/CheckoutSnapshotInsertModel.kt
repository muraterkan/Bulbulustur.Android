package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

import java.math.BigDecimal

data class CheckoutSnapshotInsertModel(
    val CheckoutKey: String = "",
    val InsertedBy: Int = 0,
    val LanguageId: Int = 0,
    val FlowType: Int = 1,
    val InvoiceAddressId: Int = 0,
    val DeliveryAddressId: Int = 0,
    val InvoiceAddressText: String = "",
    val DeliveryAddressText: String = "",
    val BaseAmount: BigDecimal = BigDecimal.ZERO,
    val PaymentAmount: BigDecimal = BigDecimal.ZERO,
    val Currency: String = "TRY",
    val InstallmentCount: Int = 1,
    val BasketSnapshot: String = ""
)
