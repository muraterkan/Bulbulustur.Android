package com.bulbulustur.android.businesslayer.Core.Model.RequestModels

import java.math.BigDecimal

data class GatewayPaymentCreateRequest(
    val IdempotencyKey: String = "",
    val OrderId: String = "",
    val OrderKey: String = "",
    val MemberId: Int = 0,
    val LanguageId: Int = 0,
    val Amount: BigDecimal = BigDecimal.ZERO,
    val Currency: String = "TRY",
    val FlowType: Int = 1,
    val PaymentMethodType: Int = 1,
    val Card: GatewayPaymentCard? = null,
    val StoredPaymentMethodId: Int? = null,
    val StoredPaymentMethodCvc: String? = null,
    val InstallmentCount: Int = 1,
    val RequireThreeDs: Boolean = true,
    val InvoiceAddressId: Int = 0,
    val DeliveryAddressId: Int = 0,
    val InvoiceAddressText: String? = null,
    val DeliveryAddressText: String? = null,
    val Items: List<GatewayPaymentLineItem> = emptyList(),
    val ReturnUrl: String? = null,
    val FailUrl: String? = null,
    val ClientIp: String? = null,
    val UserAgent: String? = null,
    val SessionId: String? = null,
    val DeviceFingerprintHash: String? = null,
    val CustomerName: String? = null,
    val CustomerEmail: String? = null,
    val CustomerPhone: String? = null,
    val BillingCity: String? = null,
    val DeliveryCity: String? = null,
    val LanguageCode: String = "tr"
)

data class GatewayPaymentCard(
    val CardHolderName: String = "",
    val CardNumber: String = "",
    val ExpireMonth: String = "",
    val ExpireYear: String = "",
    val Cvc: String = "",
    val StoreCard: Boolean = false,
    val CardAlias: String? = null
)

data class GatewayPaymentLineItem(
    val ReferenceId: String = "",
    val Name: String = "",
    val Quantity: Int = 0,
    val UnitPrice: BigDecimal = BigDecimal.ZERO,
    val TotalAmount: BigDecimal = BigDecimal.ZERO,
    val StoreId: Int? = null
)