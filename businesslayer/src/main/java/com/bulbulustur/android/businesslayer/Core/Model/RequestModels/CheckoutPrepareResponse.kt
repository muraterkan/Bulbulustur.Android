package com.bulbulustur.android.businesslayer.Core.Model.ResponseModels

import com.bulbulustur.android.businesslayer.Core.Model.RequestModels.GatewayPaymentLineItem
import java.math.BigDecimal

data class CheckoutPrepareResponse(
    val Success: Boolean = false,
    val CheckoutKey: String = "",
    val Amount: BigDecimal = BigDecimal.ZERO,
    val Currency: String = "TRY",
    val InvoiceAddressId: Int = 0,
    val DeliveryAddressId: Int = 0,
    val InvoiceAddressText: String = "",
    val DeliveryAddressText: String = "",
    val Items: List<GatewayPaymentLineItem> = emptyList(),
    val CustomerName: String? = null,
    val CustomerEmail: String? = null,
    val CustomerPhone: String? = null,
    val BillingCity: String? = null,
    val DeliveryCity: String? = null,
    val Message: String? = null
)