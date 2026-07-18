package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class MemberUpdateAddressModel(
    val MemberId: Int = 0,
    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CountryDepartmentId: Int? = null,
    val CityId: Int = 0,
    val DistrictId: Int? = null
)
