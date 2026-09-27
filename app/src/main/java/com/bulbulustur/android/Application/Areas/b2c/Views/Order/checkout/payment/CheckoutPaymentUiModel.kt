package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.payment

data class CheckoutPaymentCardUiModel(
    val bankName: String = "",
    val cardAlias: String = "",
    val maskedNumber: String = "",
    val cardBrand: String = ""
)

data class CheckoutInstallmentUiModel(
    val installmentCount: Int = 1,
    val title: String = "",
    val monthlyAmountText: String = "",
    val totalAmountText: String = "",
    val isSelected: Boolean = false
)
