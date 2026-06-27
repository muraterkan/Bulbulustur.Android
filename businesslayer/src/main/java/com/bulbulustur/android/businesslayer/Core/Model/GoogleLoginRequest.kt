package com.bulbulustur.android.businesslayer.Core.Model

data class GoogleLoginRequest(
    val IdToken: String = "",
    val LanguageId: Int = 1,
    val DeviceType: String = "Android",
    val Platform: String = "Android",
    val Browser: String = "Mobile App",
    val IPAddress: String = ""
)