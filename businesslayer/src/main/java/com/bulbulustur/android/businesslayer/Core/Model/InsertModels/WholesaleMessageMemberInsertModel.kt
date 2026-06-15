package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleMessageMemberInsertModel(
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
