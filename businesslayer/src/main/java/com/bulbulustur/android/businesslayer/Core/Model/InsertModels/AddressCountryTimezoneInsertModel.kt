package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class AddressCountryTimezoneInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val TimezoneId: Int = 0,
    val CountryId: Int = 0,
    val DisplayOrder: Int? = null
)
