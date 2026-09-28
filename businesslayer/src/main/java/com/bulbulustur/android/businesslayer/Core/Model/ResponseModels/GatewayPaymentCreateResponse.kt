package com.bulbulustur.android.businesslayer.Core.Model.ResponseModels

data class GatewayPaymentCreateResponse(
    val Success: Boolean = false,
    val PaymentIntentId: Int = 0,
    val PaymentAttemptId: Int = 0,
    val Status: Int = 0,
    val ProviderCode: String? = null,
    val AcquirerCode: String? = null,
    val ProviderReferenceId: String? = null,
    val ProviderRequestId: String? = null,
    val ProviderTransactionId: String? = null,
    val RedirectUrl: String? = null,
    val HtmlContent: String? = null,
    val Message: String? = null,
    val ErrorCode: String? = null,
    val RequiresReconciliation: Boolean = false,
    val MerchantReference: Any? = null
)