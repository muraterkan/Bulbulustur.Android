package com.bulbulustur.android.Application.Shared.Address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCategorySearchSelectInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectOption
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun AddressCascadeFields(
    state: AddressCascadeState,
    onCountrySelected: (Int) -> Unit,
    onCountryStateSelected: (Int) -> Unit,
    onCountryDepartmentSelected: (Int?) -> Unit,
    onCitySelected: (Int) -> Unit,
    onDistrictSelected: (Int?) -> Unit,
    modifier: Modifier = Modifier,
    countryLabel: String = "Ülke",
    countryPlaceholder: String = BBLocalization.Current.Get(key = "387bcc7b-e309-4099-8f1d-0ee062d4b7f4", fallback = ""),
    countryStateLabel: String = "Eyalet / Bölge",
    countryStatePlaceholder: String = "Eyalet veya bölge seçiniz",
    countryDepartmentLabel: String = "Departman",
    countryDepartmentPlaceholder: String = "Departman seçiniz",
    cityLabel: String = "Şehir",
    cityPlaceholder: String = "Şehir seçiniz",
    districtLabel: String = "İlçe",
    districtPlaceholder: String = "İlçe seçiniz",
    countryErrorText: String? = null,
    countryStateErrorText: String? = null,
    countryDepartmentErrorText: String? = null,
    cityErrorText: String? = null,
    districtErrorText: String? = null,
    enabled: Boolean = true
) {
    val countryOptions =
        state.Countries.map { country ->
            BbSelectOption(
                value =
                    country.AddressCountryId.toString(),
                text =
                    country.Content
            )
        }

    val countryStateOptions =
        state.CountryStates.map { countryState ->
            BbSelectOption(
                value =
                    countryState.AddressCountryStateId.toString(),
                text =
                    countryState.StateName
            )
        }

    val countryDepartmentOptions =
        state.CountryDepartments.map { countryDepartment ->
            BbSelectOption(
                value =
                    countryDepartment.AddressCountryDepartmentId.toString(),
                text =
                    countryDepartment.DepartmentName
            )
        }

    val cityOptions =
        state.Cities.map { city ->
            BbSelectOption(
                value =
                    city.AddressCityId.toString(),
                text =
                    city.Content
            )
        }

    val districtOptions =
        state.Districts.mapNotNull { district ->
            val districtName =
                district.Content
                    ?.trim()
                    .orEmpty()

            if (districtName.isBlank()) {
                null
            } else {
                BbSelectOption(
                    value =
                        district.AddressDistrictId.toString(),
                    text =
                        districtName
                )
            }
        }

    Column(
        modifier =
            modifier,
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space4
            )
    ) {
        BbCategorySearchSelectInput(
            selectedValue =
                state.SelectedCountryId
                    .takeIf {
                        it > 0
                    }
                    ?.toString()
                    .orEmpty(),
            options =
                countryOptions,
            onValueChange = { value ->
                onCountrySelected(
                    value.toIntOrNull()
                        ?: 0
                )
            },
            label =
                countryLabel,
            placeholder =
                if (state.IsCountriesLoading) {
                    "Ülkeler yükleniyor..."
                } else {
                    countryPlaceholder
                },
            searchPlaceholder =
                "Ülke ara...",
            errorText =
                countryErrorText
                    ?: state.CountryError,
            enabled =
                enabled &&
                        !state.IsCountriesLoading &&
                        countryOptions.isNotEmpty(),
            maximumVisibleOptionCount =
                50
        )

        if (state.ShouldShowCountryState) {
            BbCategorySearchSelectInput(
                selectedValue =
                    state.SelectedCountryStateId
                        .takeIf {
                            it > 0
                        }
                        ?.toString()
                        .orEmpty(),
                options =
                    countryStateOptions,
                onValueChange = { value ->
                    onCountryStateSelected(
                        value.toIntOrNull()
                            ?: 0
                    )
                },
                label =
                    countryStateLabel,
                placeholder =
                    if (state.IsCountryStatesLoading) {
                        "Eyalet veya bölgeler yükleniyor..."
                    } else {
                        countryStatePlaceholder
                    },
                searchPlaceholder =
                    "Eyalet veya bölge ara...",
                errorText =
                    countryStateErrorText
                        ?: state.CountryStateError,
                enabled =
                    enabled &&
                            state.SelectedCountryId > 0 &&
                            !state.IsCountryStatesLoading &&
                            countryStateOptions.isNotEmpty(),
                maximumVisibleOptionCount =
                    50
            )
        }

        if (state.ShouldShowCountryDepartment) {
            BbCategorySearchSelectInput(
                selectedValue =
                    state.SelectedCountryDepartmentId
                        ?.takeIf {
                            it > 0
                        }
                        ?.toString()
                        .orEmpty(),
                options =
                    countryDepartmentOptions,
                onValueChange = { value ->
                    onCountryDepartmentSelected(
                        value.toIntOrNull()
                    )
                },
                label =
                    countryDepartmentLabel,
                placeholder =
                    if (state.IsCountryDepartmentsLoading) {
                        "Departmanlar yükleniyor..."
                    } else {
                        countryDepartmentPlaceholder
                    },
                searchPlaceholder =
                    "Departman ara...",
                errorText =
                    countryDepartmentErrorText
                        ?: state.CountryDepartmentError,
                enabled =
                    enabled &&
                            state.SelectedCountryStateId > 0 &&
                            !state.IsCountryDepartmentsLoading &&
                            countryDepartmentOptions.isNotEmpty(),
                maximumVisibleOptionCount =
                    50
            )
        }

        BbCategorySearchSelectInput(
            selectedValue =
                state.SelectedCityId
                    .takeIf {
                        it > 0
                    }
                    ?.toString()
                    .orEmpty(),
            options =
                cityOptions,
            onValueChange = { value ->
                onCitySelected(
                    value.toIntOrNull()
                        ?: 0
                )
            },
            label =
                cityLabel,
            placeholder =
                if (state.IsCitiesLoading) {
                    "Şehirler yükleniyor..."
                } else {
                    cityPlaceholder
                },
            searchPlaceholder =
                "Şehir ara...",
            errorText =
                cityErrorText
                    ?: state.CityError,
            enabled =
                enabled &&
                        state.CanSelectCity &&
                        !state.IsCitiesLoading &&
                        cityOptions.isNotEmpty(),
            maximumVisibleOptionCount =
                50
        )

        if (state.ShouldShowDistrict) {
            BbCategorySearchSelectInput(
                selectedValue =
                    state.SelectedDistrictId
                        ?.takeIf {
                            it > 0
                        }
                        ?.toString()
                        .orEmpty(),
                options =
                    districtOptions,
                onValueChange = { value ->
                    onDistrictSelected(
                        value.toIntOrNull()
                    )
                },
                label =
                    districtLabel,
                placeholder =
                    if (state.IsDistrictsLoading) {
                        "İlçeler yükleniyor..."
                    } else {
                        districtPlaceholder
                    },
                searchPlaceholder =
                    "İlçe ara...",
                errorText =
                    districtErrorText
                        ?: state.DistrictError,
                enabled =
                    enabled &&
                            state.SelectedCityId > 0 &&
                            !state.IsDistrictsLoading &&
                            districtOptions.isNotEmpty(),
                maximumVisibleOptionCount =
                    50
            )
        }
    }
}