package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class CompanyAddressUpdateModel(
    val CompanyAddressId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CityId: Int = 0,
    val DistrictId: Int? = null,
    val PostCode: String = "",
    val Address: String = "",
    val InitLatitude: String = "",
    val InitLongitude: String = "",
    val Fax: String = "",
    val CountryDepartmentId: Int? = null
)
