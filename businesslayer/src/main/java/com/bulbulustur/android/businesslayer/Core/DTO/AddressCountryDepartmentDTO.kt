package com.bulbulustur.android.businesslayer.Core.DTO

data class AddressCountryDepartmentDTO(
    val AddressCountryDepartmentId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CountryId: Int = 0,
    val StateId: Int = 0,
    val DepartmentName: String = "",
    val GeoNameId: String = "",
    val Latitude: Double? = null,
    val Longitude: Double? = null
)
