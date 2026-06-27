package com.bulbulustur.android.businesslayer.Core.Model

data class MemberSetPasswordModel(
    val Name: String = "",
    val NewPassword: String = "",
    val ReNewPassword: String = "",
    val ActivationCode: String = "",
    val DeviceType: String = "Android",
    val IPAddress: String = "",
    val Browser: String = "",
    val Platform: String = "Android",
    val Location: String = "",
    val LanguageId: Int = 1
)