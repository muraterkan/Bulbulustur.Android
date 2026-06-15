package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class StoreAddressInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val StoreId: Int = 0,
    val AddressTypeId: Int = 0,
    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CountryDepartmentId: Int? = null,
    val CityId: Int = 0,
    val DistrictId: Int? = null,
    val PostCode: String = "",
    val Address: String = "",
    val IsDefault: Boolean = false
)
