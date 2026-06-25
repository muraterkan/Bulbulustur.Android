package com.bulbulustur.android.businesslayer.Core.Model

data class MemberRegisterModel(
    val Email: String = "",
    val Name: String = "",
    val Surname: String = "",
    val Password: String = "",
    val PasswordAgain: String = "",
    val ActivationCode: String = "",
    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CountryDepartmentId: Int? = null,
    val CityId: Int = 0,
    val DistrictId: Int? = null,
    val LoginProvider: String = "",
    val Uuid: String = "",
    val MemberSecureKey: String = "",
    val LanguageId: Int = 0
)