package com.bulbulustur.android.Application.Shared.Address

sealed interface AddressCascadeEvent {

    data class LoadCountries(
        val LanguageId: Int
    ) : AddressCascadeEvent

    data class SelectCountry(
        val CountryId: Int,
        val LanguageId: Int
    ) : AddressCascadeEvent

    data class SelectCountryState(
        val CountryStateId: Int,
        val LanguageId: Int
    ) : AddressCascadeEvent

    data class SelectCountryDepartment(
        val CountryDepartmentId: Int?,
        val LanguageId: Int
    ) : AddressCascadeEvent

    data class SelectCity(
        val CityId: Int,
        val LanguageId: Int
    ) : AddressCascadeEvent

    data class SelectDistrict(
        val DistrictId: Int?
    ) : AddressCascadeEvent

    data class SetInitialSelection(
        val Selection: AddressCascadeSelection,
        val LanguageId: Int
    ) : AddressCascadeEvent

    data object Clear :
        AddressCascadeEvent

    data object ClearErrors :
        AddressCascadeEvent
}