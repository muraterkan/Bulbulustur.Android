package com.bulbulustur.android.businesslayer.Core.DTO

data class ReviewDTO(
    val ReviewId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val SourceType: String = "",
    val ItemId: Int = 0,
    val SecureKey: String = "",
    val VariantId: Int? = null,
    val MemberId: Int = 0,
    val Content: String = "",
    val Rating: Double = 0.0,
    val ConfirmationStatusId: Int = 0
)
