package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberAddressInsertModel(
    val Ecommerce: Int = 0,
    val AddressTitle: String = "",
    val Name: String = "",
    val Surname: String = "",
    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CountryDepartmentId: Int? = null,
    val CityId: Int = 0,
    val DistrictId: Int? = null,
    val PostCode: String = "",
    val Address: String = "",
    val InitLatitude: String = "",
    val InitLongitude: String = "",
    val Phone: String = "",
    val IsDefault: Boolean = false,
    val AddressKey: String = ""
)