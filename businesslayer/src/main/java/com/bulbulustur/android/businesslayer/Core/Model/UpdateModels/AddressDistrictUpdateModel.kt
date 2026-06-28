package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class AddressDistrictUpdateModel(
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

    val DisplayOrder: Int? = null,

    val CountryName: String = "",
    val CityName: String = ""
)
