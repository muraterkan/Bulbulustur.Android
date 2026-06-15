package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class AddressCountryDepartmentInsertModel(
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
