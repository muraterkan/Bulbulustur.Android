package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleMessageMemberDTO(
    val WholesaleMessageMemberId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val MessageId: Int = 0,
    val IsRead: Boolean = false,
    val IsDeleted: Boolean = false,
    val IsStarred: Boolean = false,
    val IsPriority: Boolean = false,
    val IsTrash: Boolean = false,
    val IsArchived: Boolean = false
)
