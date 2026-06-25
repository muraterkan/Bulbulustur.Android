package com.bulbulustur.android.businesslayer.Core.Security

data class SecureTokenModel(
    val AccessToken: String = "",
    val RefreshToken: String = "",
    val Expiration: String = ""
) {
    val HasTokens: Boolean
        get() = AccessToken.isNotBlank() &&
                RefreshToken.isNotBlank() &&
                Expiration.isNotBlank()
}