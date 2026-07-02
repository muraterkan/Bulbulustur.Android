package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberFollowedStoreInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val StoreId: Int = 0
)