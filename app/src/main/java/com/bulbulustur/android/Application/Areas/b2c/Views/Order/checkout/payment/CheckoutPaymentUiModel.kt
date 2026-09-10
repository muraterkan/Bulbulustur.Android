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

object CheckoutPaymentMockData {

    val cards = listOf(
        CheckoutPaymentCardUiModel(
            bankName = "Türkiye İş Bankası",
            cardAlias = "MAXIMUM kartım",
            maskedNumber = "**** **** **** 0625",
            cardBrand = "VISA"
        ),

        CheckoutPaymentCardUiModel(
            bankName = "Yapı Kredi",
            cardAlias = "WORLD kartım",
            maskedNumber = "**** **** **** 4381",
            cardBrand = "MASTERCARD"
        )
    )

    val card = cards.first()

    val installments = listOf(
        CheckoutInstallmentUiModel(
            installmentCount = 1,
            title = "Tek Çekim",
            monthlyAmountText = "₺1.428,00",
            totalAmountText = "₺1.428,00",
            isSelected = true
        ),

        CheckoutInstallmentUiModel(
            installmentCount = 2,
            title = "2 Taksit",
            monthlyAmountText = "2 x ₺735,00",
            totalAmountText = "₺1.470,00"
        ),

        CheckoutInstallmentUiModel(
            installmentCount = 3,
            title = "3 Taksit",
            monthlyAmountText = "3 x ₺500,00",
            totalAmountText = "₺1.500,00"
        ),

        CheckoutInstallmentUiModel(
            installmentCount = 6,
            title = "6 Taksit",
            monthlyAmountText = "6 x ₺260,00",
            totalAmountText = "₺1.560,00"
        )
    )
}
