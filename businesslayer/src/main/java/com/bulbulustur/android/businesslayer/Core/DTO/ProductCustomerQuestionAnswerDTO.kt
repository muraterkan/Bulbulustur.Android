package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductCustomerQuestionAnswerDTO(
    val ProductCustomerQuestionAnswerId: Int = 0,

    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val ProductCustomerQuestionId: Int = 0,
    val StoreId: Int = 0,
    val Answer: String = "",

    val Questioner: String = "",
    val Picture: String = "",
    val ProductId: Int = 0,
    val Status: String = "",
    val Store: String = ""
)