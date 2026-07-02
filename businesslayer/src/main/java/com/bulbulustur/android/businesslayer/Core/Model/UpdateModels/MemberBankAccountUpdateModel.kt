package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class MemberBankAccountUpdateModel(
    val MemberBankAccountId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val BankId: Int = 0,
    val BankIban: String = "",
    val MemberId: Int = 0
)