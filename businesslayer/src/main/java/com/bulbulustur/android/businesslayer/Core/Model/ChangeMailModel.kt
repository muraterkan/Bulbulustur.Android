package com.bulbulustur.android.businesslayer.Core.Model

data class ChangeMailModel(
    val MemberId: Int = 0,
    val Email: String = "",
    val NewEmail: String = "",
    val ReNewEmail: String = "",
    val LanguageId: Int = 1
)