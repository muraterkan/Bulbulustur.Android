package com.bulbulustur.android.businesslayer.Core.DTO

data class AddressDistrictDTO(
    val AddressDistrictId: Int = 0,
    val CountryId: Int = 0,
    val CityId: Int = 0,
    val Content: String? = null,
    val CountryName: String? = null,
    val CityName: String? = null
)