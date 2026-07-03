package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductCustomerQuestionDTO(
    val ProductCustomerQuestionId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val StoreId: Int = 0,
    val ProductSecureKey: String? = null,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val Question: String = "",
    val ProductName: String? = null,
    val Picture: String? = null,
    val Questioner: String? = null,
    val Message: String? = null,
    val StoreName: String? = null,
    val IsAnswered: Boolean = false,
    val DefaultPicture: String? = null
)