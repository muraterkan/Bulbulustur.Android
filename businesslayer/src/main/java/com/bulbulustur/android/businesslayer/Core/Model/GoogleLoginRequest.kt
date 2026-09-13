package com.bulbulustur.android.businesslayer.Core.Model

data class GoogleLoginRequest(
    val IdToken: String = "",
    val LanguageId: Int = 1,
    val DeviceFingerprintHash: String = "",
    val Device: String = "",
    val Os: String = "",
    val Browser: String = ""
)