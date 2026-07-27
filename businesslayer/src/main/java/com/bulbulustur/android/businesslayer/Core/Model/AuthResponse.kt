package com.bulbulustur.android.businesslayer.Core.Model

data class AuthResponse(
    val Token: String = "",
    val RefreshToken: String = "",
    val Expiration: String = "",
    val Member: AuthMember = AuthMember()
)
