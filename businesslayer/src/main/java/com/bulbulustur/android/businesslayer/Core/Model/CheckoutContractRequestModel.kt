package com.bulbulustur.android.businesslayer.Core.Model

data class CheckoutContractRequestModel(val MemberId: Int = 0, val LanguageId: Int = 1, val DeliveryAddressId: Int = 0, val InvoiceAddressId: Int = 0, val InstallmentCount: Int = 1)
