package com.bulbulustur.android.businesslayer.Core.Model

data class MemberAuthModel(
    val Email: String = "",
    val Password: String = "",
    val DeviceFingerprintHash: String = "",
    val Device: String = "",
    val Os: String = "",
    val Browser: String = ""
)