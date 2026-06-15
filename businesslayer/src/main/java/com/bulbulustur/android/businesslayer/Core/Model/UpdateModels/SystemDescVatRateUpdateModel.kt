package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescVatRateUpdateModel(
    val SystemDescVatRateId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Vat: Double = 0.0,
    val Description: String = ""
)
