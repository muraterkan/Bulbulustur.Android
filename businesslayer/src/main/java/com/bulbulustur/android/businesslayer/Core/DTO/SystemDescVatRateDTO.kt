package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescVatRateDTO(
    val SystemDescVatRateId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Vat: Double = 0.0,
    val Description: String = ""
)
