package com.bulbulustur.android.businesslayer.Core.DTO

import java.math.BigDecimal

data class CheckoutSnapshotDTO(
    val CheckoutSnapshotId: Int = 0,
    val CheckoutKey: String = "",
    val InsertedBy: Int = 0,
    val InsertedDate: String? = null,
    val UpdatedDate: String? = null,
    val CompletedDate: String? = null,
    val StatusId: Int = 0,
    val LanguageId: Int = 0,
    val FlowType: Int = 0,
    val InvoiceAddressId: Int = 0,
    val DeliveryAddressId: Int = 0,
    val InvoiceAddressText: String? = null,
    val DeliveryAddressText: String? = null,
    val BasketSnapshot: String? = null,
    val BaseAmount: BigDecimal = BigDecimal.ZERO,
    val PaymentAmount: BigDecimal = BigDecimal.ZERO,
    val Currency: String = "TRY",
    val InstallmentCount: Int = 1,
    val PaymentIntentId: Int? = null,
    val PaymentAttemptId: Int? = null,
    val OrderId: Int? = null,
    val OrderKey: String? = null
)
