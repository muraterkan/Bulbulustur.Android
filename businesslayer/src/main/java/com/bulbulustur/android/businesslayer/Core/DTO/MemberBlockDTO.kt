package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberBlockDTO(
    val MemberBlockId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val Description: String = "",
    val EndBlockDate: String = ""
)
