package com.bulbulustur.android.businesslayer.Core.Model.RequestModels

data class CheckoutPrepareRequest(
    val MemberId: Int = 0,
    val LanguageId: Int = 0,
    val InvoiceAddressId: Int = 0,
    val DeliveryAddressId: Int = 0,
    val InstallmentCount: Int = 1
)