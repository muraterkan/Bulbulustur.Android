package com.bulbulustur.android.businesslayer.Core.DTO

data class AddressCountryDTO(
    val AddressCountryId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val GeoNameId: String = "",
    val ContinentId: Int = 0,
    val ContinentAreaId: Int? = null,
    val Content: String = "",
    val Code: String = "",
    val IsoShortCode: String = "",
    val IsoLongCode: String = "",
    val PhoneCode: String = "",
    val Flag: String = "",
    val Picture: String = "",
    val LanguageId: Int = 0,
    val Area: Double = 0.0,
    val DisplayOrder: Int? = null,
    val HasStates: Boolean? = null,
    val HasDepartments: Boolean? = null
)
