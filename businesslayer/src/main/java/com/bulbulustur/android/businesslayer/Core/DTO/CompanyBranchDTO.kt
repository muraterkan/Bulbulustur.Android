package com.bulbulustur.android.businesslayer.Core.DTO

data class CompanyBranchDTO(
    val CompanyBranchId: Int = 0,
    val StatusId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val CompanyId: Int = 0,
    val BranchCode: String = "",
    val BranchName: String = "",
    val IsMainBranch: Boolean = false,
    val CountryId: Int = 0,
    val CountryStateId: Int? = null,
    val CityId: Int = 0,
    val DistrictId: Int? = null,
    val PostCode: String = "",
    val Address: String = "",
    val Email: String = "",
    val Phone: String = "",
    val Fax: String = "",
    val TaxOffice: String = "",
    val TaxId: String = "",
    val MersisNo: String = "",
    val KepAddress: String = "",
    val CurrencyId: Int = 0,
    val CountryDepartmentId: Int = 0
)
