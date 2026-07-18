package com.bulbulustur.android.businesslayer.Core.Model

data class ChangePasswordAsyncModel(
    val MemberId: Int = 0,
    val NewPassword: String = "",
    val ReNewPassword: String = "",
    val ActivePassword: String = "",
    val LanguageId: Int = 1
)