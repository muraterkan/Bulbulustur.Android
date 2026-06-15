package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberActivityInsertModel(
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val AccountActivityTypeId: Int = 0,
    val ActivityDate: String = "",
    val AccountActivityReasonId: Int = 0
)
