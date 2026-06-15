package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescCargoDesiPriceDTO(
    val SystemDescCargoDesiPriceId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CargoCompanyId: Int = 0,
    val SystemDescDesiId: Int = 0,
    val NetPrice: Double = 0.0,
    val GrossPrice: Double? = null,
    val Content: String = ""
)
