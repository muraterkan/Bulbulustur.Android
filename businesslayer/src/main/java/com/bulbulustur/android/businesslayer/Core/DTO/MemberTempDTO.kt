package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberTempDTO(
    val MemberTempTempId: Int = 0,
    val Email: String = "",
    val ActivationCode: String = "",
    val ExpirationDate: String = "",
    val InsertedDate: String = ""
)