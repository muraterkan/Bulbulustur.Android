package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class AddressCityUpdateModel(
    val AddressCityId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CountryDepartmentId: Int? = null,
    val Content: String = "",
    val Code: String = "",
    val Latitude: String = "",
    val Longitude: String = "",
    val Population: Long? = null,
    val DisplayOrder: Int? = null,
    val Timezone: String = "",
    val StateCode: String = ""
)
