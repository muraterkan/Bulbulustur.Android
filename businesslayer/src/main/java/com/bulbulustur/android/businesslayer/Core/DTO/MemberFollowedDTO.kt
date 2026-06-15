package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberFollowedDTO(
    val MemberFollowedId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val FollowedTypeId: Int = 0,
    val ItemId: Int = 0
)
