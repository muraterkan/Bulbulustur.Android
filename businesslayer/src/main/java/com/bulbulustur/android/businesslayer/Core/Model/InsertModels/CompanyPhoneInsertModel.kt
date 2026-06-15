package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class CompanyPhoneInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Phone: String = "",
    val Priority: Int = 0,
    val CompanyId: Int = 0
)
