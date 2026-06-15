package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class AddressNeighborhoodInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val CountryStateId: Int = 0,
    val CityId: Int = 0,
    val DistrictId: Int? = null,
    val DistrictName: String = "",
    val Pk: Double? = null,
    val SemtBucakBelde: String = "",
    val Type: Int? = null,
    val PostalCode: String = "",
    val Latitude: Double? = null,
    val Longitude: Double? = null,
    val DisplayOrder: Int? = null
)
