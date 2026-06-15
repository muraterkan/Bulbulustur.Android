package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescVatRateInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Vat: Double = 0.0,
    val Description: String = ""
)
