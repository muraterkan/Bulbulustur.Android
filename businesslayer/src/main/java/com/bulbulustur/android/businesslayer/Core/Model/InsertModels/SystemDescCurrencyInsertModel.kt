package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescCurrencyInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Content: String = "",
    val CurrencySymbol: String = "",
    val IsoCode: String = ""
)
