package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class CompanyBankAccountUpdateModel(
    val CompanyBankAccountId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val BankId: Int = 0,
    val BankIban: String = "",
    val Default: Int = 0,
    val SecureKey: String = ""
)
