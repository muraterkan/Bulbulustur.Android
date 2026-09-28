package com.bulbulustur.android.businesslayer.Core.Model.ResponseModels

import java.math.BigDecimal

data class GatewayPaymentStatusResponse(
    val PaymentIntentId: Int = 0,
    val IdempotencyKey: String = "",
    val OrderId: String = "",
    val MemberId: String = "",
    val Amount: BigDecimal = BigDecimal.ZERO,
    val Currency: String = "",
    val InstallmentCount: Int = 1,
    val RequireThreeDs: Boolean = false,
    val Status: Int = 0,
    val SuccessfulPaymentAttemptId: Int? = null,
    val FinalProviderCode: String? = null,
    val FinalAcquirerCode: String? = null,
    val CompletedDate: String? = null
)