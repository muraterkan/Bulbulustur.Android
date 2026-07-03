package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberPhoneDTO(
    val MemberPhoneId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Phone: String = "",
    val VerificationCode: String = "",
    val Verified: Boolean = false,
    val IsDefault: Boolean = false,
    val MemberName: String = "",
    val ReturnUrl: String = ""
)