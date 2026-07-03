package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberPhoneInsertModel

data class MemberPhoneUpdateModel(
    val MemberPhoneId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Phone: String = "",
    val VerificationCode: String = "",
    val Verified: Boolean = false,
    val IsDefault: Boolean = false
)