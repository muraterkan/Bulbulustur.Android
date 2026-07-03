package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberLoginActivityDTO(
    val MemberId: Int = 0,
    val InsertedDate: String = "",
    val LogId: Int = 0,
    val Ip: String = "",
    val Device: String = "",
    val Os: String = "",
    val Browser: String = "",
    val LoginProvider: String = ""
)