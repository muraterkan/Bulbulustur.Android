package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberFollowedCompanyInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0
)