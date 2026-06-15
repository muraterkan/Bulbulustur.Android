package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductVariantPriceDTO(
    val ProductVariantPriceId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val StoreId: Int = 0,
    val ProductId: Int = 0,
    val ProductSecureKey: String = "",
    val VariantId: Int = 0,
    val VariantSecureKey: String = "",
    val Price: Double = 0.0,
    val CurrencyId: Int = 0,
    val Stock: Int = 0,
    val Stockcode: String = "",
    val ConfirmationStatusId: Int = 0,
    val Bpin: String = "",
    val StoreBoxRuleId: Int = 0
)
