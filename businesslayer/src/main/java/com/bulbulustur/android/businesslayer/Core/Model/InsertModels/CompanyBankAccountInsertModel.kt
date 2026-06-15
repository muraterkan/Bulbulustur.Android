package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class CompanyBankAccountInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val BankId: Int = 0,
    val BankIban: String = "",
    val Default: Int = 0,
    val SecureKey: String = ""
)
