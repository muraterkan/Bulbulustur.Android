package com.bulbulustur.android.Application.Shared.Address

data class AddressCascadeSelection(
    val CountryId: Int = 0,
    val CountryStateId: Int = 0,
    val CountryDepartmentId: Int? = null,
    val CityId: Int = 0,
    val DistrictId: Int? = null
) {

    val HasCountry: Boolean
        get() =
            CountryId > 0

    val HasCountryState: Boolean
        get() =
            CountryStateId > 0

    val HasCountryDepartment: Boolean
        get() =
            CountryDepartmentId != null &&
                    CountryDepartmentId > 0

    val HasCity: Boolean
        get() =
            CityId > 0

    val HasDistrict: Boolean
        get() =
            DistrictId != null &&
                    DistrictId > 0

    val IsTurkey: Boolean
        get() =
            CountryId == TURKEY_COUNTRY_ID

    val IsLocationSelected: Boolean
        get() =
            HasCountry &&
                    HasCity

    fun Normalize(): AddressCascadeSelection {
        return copy(
            CountryStateId =
                CountryStateId.coerceAtLeast(
                    0
                ),
            CountryDepartmentId =
                CountryDepartmentId
                    ?.takeIf {
                        it > 0
                    },
            CityId =
                CityId.coerceAtLeast(
                    0
                ),
            DistrictId =
                DistrictId
                    ?.takeIf {
                        it > 0
                    }
        )
    }

    companion object {

        const val TURKEY_COUNTRY_ID: Int =
            209

        val Empty =
            AddressCascadeSelection()
    }
}