package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberPhoneInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Phone: String = "",
    val VerificationCode: String = "",
    val Verified: Boolean = false,
    val IsDefault: Boolean = false
)