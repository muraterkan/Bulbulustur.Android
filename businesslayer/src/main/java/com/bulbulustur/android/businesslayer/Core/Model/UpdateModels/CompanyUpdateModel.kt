package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class CompanyUpdateModel(
    val CompanyId: Int = 0,
    val CompanyTypeId: Int = 0,
    val CompanyName: String = "",
    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CountryDepartmentId: Int? = null,
    val CityId: Int = 0,
    val DistrictId: Int? = null,
    val PostCode: String = "",
    val Address: String = "",
    val Url: String = "",
    val MersisNo: String = "",
    val KepAddress: String = "",
    val TaxOffice: String = "",
    val TaxId: String = "",
    val YearEstablished: String = "",
    val Email: String = ""
)