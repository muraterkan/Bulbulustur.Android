package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class CompanyExtendedInformationUpdateModel(
    val ExtendedInformationId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val CompanyId: Int = 0,
    val StatusId: Int = 0,
    val NumberOfEmployeeId: Int = 0,
    val IndustrialZoneId: Int = 0,
    val TotalAnnualRevenue: Double = 0.0,
    val AnnualExportRevenue: Double = 0.0,
    val Area: String = "",
    val Uuid: String = "",
    val About: String = "",
    val WhyUs: String = "",
    val CurrencyId: Int = 0
)
