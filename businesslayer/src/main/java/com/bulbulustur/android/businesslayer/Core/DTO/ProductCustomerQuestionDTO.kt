package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductCustomerQuestionDTO(
    val ProductCustomerQuestionId: Int = 0,

    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val StoreId: Int = 0,
    val ProductSecureKey: String = "",
    val ProductId: Int = 0,

    /*
     * Backend insert modelinde şu anda "VarianId" typo'su bulunuyor.
     * V1 akışında bu alan 0 gönderilebilir.
     */
    val VariantId: Int = 0,

    val Question: String = "",

    val ProductName: String = "",
    val Picture: String = "",
    val Questioner: String = "",
    val Message: String = "",
    val StoreName: String = "",
    val DefaultPicture: String = ""
)