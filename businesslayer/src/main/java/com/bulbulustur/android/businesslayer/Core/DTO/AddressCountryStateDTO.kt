package com.bulbulustur.android.businesslayer.Core.DTO

data class AddressCountryStateDTO(
    val AddressCountryStateId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CountryId: Int = 0,
    val StateName: String = "",
    val StateCode: String = "",
    val GeoNameId: String = "",
    val HasAdministration: Boolean = false,
    val Latitude: Double? = null,
    val Longitude: Double? = null,
    val Area: Double? = null,
    val Flag: String = "",
    val Picture: String = "",
    val Note: String = "",
    val DisplayOrder: Int? = null,
    val Type: String = "",
    val ParentStateId: Int? = null
)
