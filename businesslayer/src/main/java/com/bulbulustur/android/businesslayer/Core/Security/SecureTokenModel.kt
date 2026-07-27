package com.bulbulustur.android.businesslayer.Core.Security

data class SecureTokenModel(
    val AccessToken: String = "",
    val RefreshToken: String = "",
    val Expiration: String = "",
    val MemberId: Int = 0,
    val MemberName: String = "",
    val MemberSurname: String = "",
    val MemberFullName: String = "",
    val MemberProfession: String = "",
    val MemberPicture: String = ""
) {

    val HasTokens: Boolean
        get() =
            AccessToken.isNotBlank() &&
                    RefreshToken.isNotBlank() &&
                    Expiration.isNotBlank() &&
                    MemberId > 0
}
