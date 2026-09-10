package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.payment

data class CheckoutCardEntryModel(
    val cardNumber: String = "",
    val expiry: String = "",
    val cvc: String = "",
    val cardHolderName: String = "",
    val storeCard: Boolean = false
) {
    val normalizedCardNumber: String get() = cardNumber.filter(Char::isDigit)
    val last4: String get() = normalizedCardNumber.takeLast(4)
    val maskedNumber: String get() = if (last4.isBlank()) "" else "**** **** **** $last4"

    val isComplete: Boolean
        get() = normalizedCardNumber.length in 13..19 &&
            expiry.filter(Char::isDigit).length == 4 &&
            cvc.length in 3..4 &&
            cardHolderName.isNotBlank()
}
