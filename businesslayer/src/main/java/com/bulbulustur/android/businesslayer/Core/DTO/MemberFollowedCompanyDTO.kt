package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberFollowedCompanyDTO(
    val MemberFollowedCompanyId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val CompanyName: String = "",
    val Logo: String = ""
)