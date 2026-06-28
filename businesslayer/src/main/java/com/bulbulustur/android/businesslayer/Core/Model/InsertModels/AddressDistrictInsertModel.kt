package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class AddressDistrictInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CountryDepartmentId: Int? = null,
    val CityId: Int = 0,

    val Content: String = "",
    val Code: String = "",

    val Latitude: Double? = null,
    val Longitude: Double? = null,

    val DisplayOrder: Int? = null
)