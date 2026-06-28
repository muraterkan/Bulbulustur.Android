package com.bulbulustur.android.Application.Shared.Address

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDepartmentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryStateDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressDistrictDTO

data class AddressCascadeState(
    val Countries: List<AddressCountryDTO> =
        emptyList(),

    val CountryStates: List<AddressCountryStateDTO> =
        emptyList(),

    val CountryDepartments: List<AddressCountryDepartmentDTO> =
        emptyList(),

    val Cities: List<AddressCityDTO> =
        emptyList(),

    val Districts: List<AddressDistrictDTO> =
        emptyList(),

    val Selection: AddressCascadeSelection =
        AddressCascadeSelection.Empty,

    val IsCountriesLoading: Boolean =
        false,

    val IsCountryStatesLoading: Boolean =
        false,

    val IsCountryDepartmentsLoading: Boolean =
        false,

    val IsCitiesLoading: Boolean =
        false,

    val IsDistrictsLoading: Boolean =
        false,

    val CountryError: String? =
        null,

    val CountryStateError: String? =
        null,

    val CountryDepartmentError: String? =
        null,

    val CityError: String? =
        null,

    val DistrictError: String? =
        null,

    val IsInitialized: Boolean =
        false
) {

    val SelectedCountryId: Int
        get() =
            Selection.CountryId

    val SelectedCountryStateId: Int
        get() =
            Selection.CountryStateId

    val SelectedCountryDepartmentId: Int?
        get() =
            Selection.CountryDepartmentId

    val SelectedCityId: Int
        get() =
            Selection.CityId

    val SelectedDistrictId: Int?
        get() =
            Selection.DistrictId

    val HasCountryStates: Boolean
        get() =
            CountryStates.isNotEmpty()

    val HasCountryDepartments: Boolean
        get() =
            CountryDepartments.isNotEmpty()

    val HasCities: Boolean
        get() =
            Cities.isNotEmpty()

    val HasDistricts: Boolean
        get() =
            Districts.isNotEmpty()

    val ShouldShowCountryState: Boolean
        get() =
            HasCountryStates ||
                    IsCountryStatesLoading

    val ShouldShowCountryDepartment: Boolean
        get() =
            HasCountryDepartments ||
                    IsCountryDepartmentsLoading

    val ShouldShowDistrict: Boolean
        get() =
            Selection.IsTurkey &&
                    (
                            HasDistricts ||
                                    IsDistrictsLoading ||
                                    Selection.HasCity
                            )

    val IsLoading: Boolean
        get() =
            IsCountriesLoading ||
                    IsCountryStatesLoading ||
                    IsCountryDepartmentsLoading ||
                    IsCitiesLoading ||
                    IsDistrictsLoading

    val ErrorMessage: String?
        get() =
            CountryError
                ?: CountryStateError
                ?: CountryDepartmentError
                ?: CityError
                ?: DistrictError

    val IsValid: Boolean
        get() =
            Selection.IsLocationSelected &&
                    (
                            !Selection.IsTurkey ||
                                    Selection.HasDistrict
                            )

    fun ClearCountrySelection(): AddressCascadeState {
        return copy(
            CountryStates =
                emptyList(),
            CountryDepartments =
                emptyList(),
            Cities =
                emptyList(),
            Districts =
                emptyList(),
            Selection =
                AddressCascadeSelection.Empty,
            IsCountryStatesLoading =
                false,
            IsCountryDepartmentsLoading =
                false,
            IsCitiesLoading =
                false,
            IsDistrictsLoading =
                false,
            CountryStateError =
                null,
            CountryDepartmentError =
                null,
            CityError =
                null,
            DistrictError =
                null
        )
    }

    fun ClearAfterCountry(
        countryId: Int
    ): AddressCascadeState {
        return copy(
            CountryStates =
                emptyList(),
            CountryDepartments =
                emptyList(),
            Cities =
                emptyList(),
            Districts =
                emptyList(),
            Selection =
                AddressCascadeSelection(
                    CountryId =
                        countryId
                ),
            IsCountryStatesLoading =
                false,
            IsCountryDepartmentsLoading =
                false,
            IsCitiesLoading =
                false,
            IsDistrictsLoading =
                false,
            CountryStateError =
                null,
            CountryDepartmentError =
                null,
            CityError =
                null,
            DistrictError =
                null
        )
    }

    fun ClearAfterCountryState(
        countryStateId: Int
    ): AddressCascadeState {
        return copy(
            CountryDepartments =
                emptyList(),
            Cities =
                emptyList(),
            Districts =
                emptyList(),
            Selection =
                Selection.copy(
                    CountryStateId =
                        countryStateId,
                    CountryDepartmentId =
                        null,
                    CityId =
                        0,
                    DistrictId =
                        null
                ),
            IsCountryDepartmentsLoading =
                false,
            IsCitiesLoading =
                false,
            IsDistrictsLoading =
                false,
            CountryDepartmentError =
                null,
            CityError =
                null,
            DistrictError =
                null
        )
    }

    fun ClearAfterCountryDepartment(
        countryDepartmentId: Int?
    ): AddressCascadeState {
        return copy(
            Cities =
                emptyList(),
            Districts =
                emptyList(),
            Selection =
                Selection.copy(
                    CountryDepartmentId =
                        countryDepartmentId
                            ?.takeIf {
                                it > 0
                            },
                    CityId =
                        0,
                    DistrictId =
                        null
                ),
            IsCitiesLoading =
                false,
            IsDistrictsLoading =
                false,
            CityError =
                null,
            DistrictError =
                null
        )
    }

    fun ClearAfterCity(
        cityId: Int
    ): AddressCascadeState {
        return copy(
            Districts =
                emptyList(),
            Selection =
                Selection.copy(
                    CityId =
                        cityId,
                    DistrictId =
                        null
                ),
            IsDistrictsLoading =
                false,
            DistrictError =
                null
        )
    }

    fun ClearErrors(): AddressCascadeState {
        return copy(
            CountryError =
                null,
            CountryStateError =
                null,
            CountryDepartmentError =
                null,
            CityError =
                null,
            DistrictError =
                null
        )
    }
}