package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescCargoDesiPriceDTO(
    val systemDescCargoDesiPriceId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val cargoCompanyId: Int,
    val systemDescDesiId: Int,
    val netPrice: Double,
    val grossPrice: Double?,
    val content: String
)