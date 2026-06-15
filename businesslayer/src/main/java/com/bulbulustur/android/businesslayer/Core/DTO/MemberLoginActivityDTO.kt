package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberLoginActivityDTO(
    val MemberLoginActivityId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val Ip: String = "",
    val Device: String = "",
    val Os: String = "",
    val Browser: String = "",
    val LoginProvider: String = ""
)
