package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class MemberAddressUpdateModel(
    val MemberAddressId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Ecommerce: Int = 0,
    val MemberId: Int = 0,
    val AddressTitle: String = "",
    val Name: String = "",
    val Surname: String = "",
    val CountryId: Int? = null,
    val CountryStateId: Int? = null,
    val CountryDepartmentId: Int? = null,
    val CityId: Int? = null,
    val DistrictId: Int? = null,
    val PostCode: String = "",
    val Address: String = "",
    val InitLatitude: String = "",
    val InitLongitude: String = "",
    val Phone: String = "",
    val IsDefault: Boolean = false,
    val AddressKey: String = ""
)
