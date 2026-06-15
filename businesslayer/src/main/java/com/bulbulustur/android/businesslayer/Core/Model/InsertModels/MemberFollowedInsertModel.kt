package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberFollowedInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val FollowedTypeId: Int = 0,
    val ItemId: Int = 0
)
