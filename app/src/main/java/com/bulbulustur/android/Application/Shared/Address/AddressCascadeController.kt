package com.bulbulustur.android.Application.Shared.Address

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Areas.b2c.Controllers.BaseController
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDepartmentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryStateDTO
import com.bulbulustur.android.businesslayer.Core.DTO.AddressDistrictDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCityRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryDepartmentRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryStateRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressDistrictRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import android.util.Log

class AddressCascadeController(
    private val executeService: IExecuteService,
    private val addressCountryRepository: IAddressCountryRepository,
    private val addressCountryStateRepository: IAddressCountryStateRepository,
    private val addressCountryDepartmentRepository: IAddressCountryDepartmentRepository,
    private val addressCityRepository: IAddressCityRepository,
    private val addressDistrictRepository: IAddressDistrictRepository
) : BaseController() {

    private val _state =
        MutableStateFlow(
            AddressCascadeState()
        )

    val State: StateFlow<AddressCascadeState> =
        _state.asStateFlow()

    fun OnEvent(
        event: AddressCascadeEvent
    ) {
        when (event) {
            is AddressCascadeEvent.LoadCountries -> {
                LoadCountries(
                    languageId =
                        event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectCountry -> {
                SelectCountry(
                    countryId =
                        event.CountryId,
                    languageId =
                        event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectCountryState -> {
                SelectCountryState(
                    countryStateId =
                        event.CountryStateId,
                    languageId =
                        event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectCountryDepartment -> {
                SelectCountryDepartment(
                    countryDepartmentId =
                        event.CountryDepartmentId,
                    languageId =
                        event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectCity -> {
                SelectCity(
                    cityId =
                        event.CityId,
                    languageId =
                        event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectDistrict -> {
                SelectDistrict(
                    districtId =
                        event.DistrictId
                )
            }

            is AddressCascadeEvent.SetInitialSelection -> {
                SetInitialSelection(
                    selection =
                        event.Selection,
                    languageId =
                        event.LanguageId
                )
            }

            AddressCascadeEvent.Clear -> {
                Clear()
            }

            AddressCascadeEvent.ClearErrors -> {
                ClearErrors()
            }
        }
    }

    fun LoadCountries(
        languageId: Int
    ) {
        viewModelScope.launch {
            LoadCountriesInternal(
                languageId =
                    languageId
            )
        }
    }

    fun SelectCountry(
        countryId: Int,
        languageId: Int
    ) {
        viewModelScope.launch {
            SelectCountryInternal(
                countryId =
                    countryId,
                languageId =
                    languageId
            )
        }
    }

    fun SelectCountryState(
        countryStateId: Int,
        languageId: Int
    ) {
        viewModelScope.launch {
            SelectCountryStateInternal(
                countryStateId =
                    countryStateId,
                languageId =
                    languageId
            )
        }
    }

    fun SelectCountryDepartment(
        countryDepartmentId: Int?,
        languageId: Int
    ) {
        viewModelScope.launch {
            SelectCountryDepartmentInternal(
                countryDepartmentId =
                    countryDepartmentId,
                languageId =
                    languageId
            )
        }
    }

    fun SelectCity(
        cityId: Int,
        languageId: Int
    ) {
        viewModelScope.launch {
            SelectCityInternal(
                cityId =
                    cityId,
                languageId =
                    languageId
            )
        }
    }

    fun SelectDistrict(
        districtId: Int?
    ) {
        _state.update {
            it.copy(
                Selection =
                    it.Selection.copy(
                        DistrictId =
                            districtId
                                ?.takeIf { value ->
                                    value > 0
                                }
                    ),
                DistrictError =
                    null
            )
        }
    }

    fun SetInitialSelection(
        selection: AddressCascadeSelection,
        languageId: Int
    ) {
        viewModelScope.launch {
            val normalizedSelection =
                selection.Normalize()

            LoadCountriesInternal(
                languageId =
                    languageId
            )

            if (!normalizedSelection.HasCountry) {
                _state.update {
                    it.copy(
                        IsInitialized =
                            true
                    )
                }

                return@launch
            }

            SelectCountryInternal(
                countryId =
                    normalizedSelection.CountryId,
                languageId =
                    languageId,
                initialSelection =
                    normalizedSelection
            )

            _state.update {
                it.copy(
                    IsInitialized =
                        true
                )
            }
        }
    }

    fun Clear() {
        _state.value =
            AddressCascadeState()
    }

    fun ClearErrors() {
        _state.update {
            it.ClearErrors()
        }
    }

    fun GetSelection(): AddressCascadeSelection {
        return _state.value.Selection
    }

    private suspend fun LoadCountriesInternal(
        languageId: Int
    ): List<AddressCountryDTO> {
        _state.update {
            it.copy(
                IsCountriesLoading =
                    true,
                CountryError =
                    null
            )
        }

        val response =
            executeService.GetAsync(
                cacheKey =
                    "AddressCascade.Countries.Language.$languageId.Count.$COUNTRY_COUNT"
            ) {
                addressCountryRepository.GetAddressCountriesAsync(
                    languageId =
                        languageId,
                    count =
                        COUNTRY_COUNT
                )
            }

        val countries: List<AddressCountryDTO> =
            if (response.Success) {
                response.Data.orEmpty()
            } else {
                emptyList()
            }

        _state.update {
            it.copy(
                Countries =
                    countries,
                IsCountriesLoading =
                    false,
                CountryError =
                    if (response.Success) {
                        null
                    } else {
                        response.Message
                            ?: DEFAULT_COUNTRY_ERROR
                    }
            )
        }

        return countries
    }

    private suspend fun SelectCountryInternal(
        countryId: Int,
        languageId: Int,
        initialSelection: AddressCascadeSelection? = null
    ) {
        if (countryId <= 0) {
            _state.update {
                it.ClearCountrySelection()
            }

            return
        }

        _state.update {
            it.ClearAfterCountry(
                countryId =
                    countryId
            )
        }

        val countryStates =
            LoadCountryStatesInternal(
                countryId =
                    countryId
            )

        if (countryStates.isEmpty()) {
            LoadCitiesInternal(
                languageId =
                    languageId,
                countryId =
                    countryId,
                countryStateId =
                    0,
                countryDepartmentId =
                    null
            )

            val initialCityId =
                initialSelection
                    ?.CityId
                    ?.takeIf {
                        it > 0
                    }

            if (initialCityId != null) {
                SelectCityInternal(
                    cityId =
                        initialCityId,
                    languageId =
                        languageId,
                    initialDistrictId =
                        initialSelection.DistrictId
                )
            }

            return
        }

        val initialCountryStateId =
            initialSelection
                ?.CountryStateId
                ?.takeIf {
                    it > 0
                }

        if (initialCountryStateId != null) {
            SelectCountryStateInternal(
                countryStateId =
                    initialCountryStateId,
                languageId =
                    languageId,
                initialSelection =
                    initialSelection
            )
        }
    }

    private suspend fun LoadCountryStatesInternal(
        countryId: Int
    ): List<AddressCountryStateDTO> {
        _state.update {
            it.copy(
                IsCountryStatesLoading =
                    true,
                CountryStateError =
                    null
            )
        }

        val response =
            executeService.GetAsync(
                cacheKey =
                    "AddressCascade.CountryStates.Country.$countryId.Count.$ITEM_COUNT"
            ) {
                addressCountryStateRepository.GetCountryStatesAsync(
                    countryId =
                        countryId,
                    count =
                        ITEM_COUNT
                )
            }

        val countryStates: List<AddressCountryStateDTO> =
            if (response.Success) {
                response.Data.orEmpty()
            } else {
                emptyList()
            }

        _state.update {
            it.copy(
                CountryStates =
                    countryStates,
                IsCountryStatesLoading =
                    false,
                CountryStateError =
                    if (response.Success) {
                        null
                    } else {
                        response.Message
                            ?: DEFAULT_COUNTRY_STATE_ERROR
                    }
            )
        }

        return countryStates
    }

    private suspend fun SelectCountryStateInternal(
        countryStateId: Int,
        languageId: Int,
        initialSelection: AddressCascadeSelection? = null
    ) {
        val currentCountryId =
            _state.value.Selection.CountryId

        if (
            currentCountryId <= 0 ||
            countryStateId <= 0
        ) {
            _state.update {
                it.ClearAfterCountryState(
                    countryStateId =
                        0
                )
            }

            return
        }

        _state.update {
            it.ClearAfterCountryState(
                countryStateId =
                    countryStateId
            )
        }

        val countryDepartments =
            LoadCountryDepartmentsInternal(
                countryId =
                    currentCountryId,
                countryStateId =
                    countryStateId
            )

        if (countryDepartments.isEmpty()) {
            LoadCitiesInternal(
                languageId =
                    languageId,
                countryId =
                    currentCountryId,
                countryStateId =
                    countryStateId,
                countryDepartmentId =
                    null
            )

            val initialCityId =
                initialSelection
                    ?.CityId
                    ?.takeIf {
                        it > 0
                    }

            if (initialCityId != null) {
                SelectCityInternal(
                    cityId =
                        initialCityId,
                    languageId =
                        languageId,
                    initialDistrictId =
                        initialSelection.DistrictId
                )
            }

            return
        }

        val initialCountryDepartmentId =
            initialSelection
                ?.CountryDepartmentId
                ?.takeIf {
                    it > 0
                }

        if (initialCountryDepartmentId != null) {
            SelectCountryDepartmentInternal(
                countryDepartmentId =
                    initialCountryDepartmentId,
                languageId =
                    languageId,
                initialSelection =
                    initialSelection
            )
        }
    }

    private suspend fun LoadCountryDepartmentsInternal(
        countryId: Int,
        countryStateId: Int
    ): List<AddressCountryDepartmentDTO> {
        _state.update {
            it.copy(
                IsCountryDepartmentsLoading =
                    true,
                CountryDepartmentError =
                    null
            )
        }

        val response =
            executeService.GetAsync(
                cacheKey =
                    buildString {
                        append("AddressCascade.CountryDepartments")
                        append(".Country.$countryId")
                        append(".State.$countryStateId")
                        append(".Count.$ITEM_COUNT")
                    }
            ) {
                addressCountryDepartmentRepository.GetCountryDepartmentsAsync(
                    countryId =
                        countryId,
                    countryStateId =
                        countryStateId,
                    count =
                        ITEM_COUNT
                )
            }

        val countryDepartments: List<AddressCountryDepartmentDTO> =
            if (response.Success) {
                response.Data.orEmpty()
            } else {
                emptyList()
            }

        _state.update {
            it.copy(
                CountryDepartments =
                    countryDepartments,
                IsCountryDepartmentsLoading =
                    false,
                CountryDepartmentError =
                    if (response.Success) {
                        null
                    } else {
                        response.Message
                            ?: DEFAULT_COUNTRY_DEPARTMENT_ERROR
                    }
            )
        }

        return countryDepartments
    }

    private suspend fun SelectCountryDepartmentInternal(
        countryDepartmentId: Int?,
        languageId: Int,
        initialSelection: AddressCascadeSelection? = null
    ) {
        val currentSelection =
            _state.value.Selection

        val normalizedCountryDepartmentId =
            countryDepartmentId
                ?.takeIf {
                    it > 0
                }

        _state.update {
            it.ClearAfterCountryDepartment(
                countryDepartmentId =
                    normalizedCountryDepartmentId
            )
        }

        if (currentSelection.CountryId <= 0) {
            return
        }

        LoadCitiesInternal(
            languageId =
                languageId,
            countryId =
                currentSelection.CountryId,
            countryStateId =
                currentSelection.CountryStateId,
            countryDepartmentId =
                normalizedCountryDepartmentId
        )

        val initialCityId =
            initialSelection
                ?.CityId
                ?.takeIf {
                    it > 0
                }

        if (initialCityId != null) {
            SelectCityInternal(
                cityId =
                    initialCityId,
                languageId =
                    languageId,
                initialDistrictId =
                    initialSelection.DistrictId
            )
        }
    }

    private suspend fun LoadCitiesInternal(
        languageId: Int,
        countryId: Int,
        countryStateId: Int,
        countryDepartmentId: Int?
    ): List<AddressCityDTO> {
        _state.update {
            it.copy(
                IsCitiesLoading =
                    true,
                CityError =
                    null,
                Cities =
                    emptyList(),
                Districts =
                    emptyList()
            )
        }

        val response =
            executeService.GetAsync(
                cacheKey =
                    buildString {
                        append("AddressCascade.Cities")
                        append(".Language.$languageId")
                        append(".Country.$countryId")
                        append(".State.$countryStateId")
                        append(".Department.${countryDepartmentId ?: 0}")
                        append(".Count.$ITEM_COUNT")
                    }
            ) {
                addressCityRepository.GetAddressCitiesAsync(
                    languageId =
                        languageId,
                    countryId =
                        countryId,
                    countryStateId =
                        countryStateId,
                    countryDepartmentId =
                        countryDepartmentId,
                    count =
                        ITEM_COUNT
                )
            }

        val cities: List<AddressCityDTO> =
            if (response.Success) {
                response.Data.orEmpty()
            } else {
                emptyList()
            }

        _state.update {
            it.copy(
                Cities =
                    cities,
                IsCitiesLoading =
                    false,
                CityError =
                    if (response.Success) {
                        null
                    } else {
                        response.Message
                            ?: DEFAULT_CITY_ERROR
                    }
            )
        }

        return cities
    }

    private suspend fun SelectCityInternal(
        cityId: Int,
        languageId: Int,
        initialDistrictId: Int? = null
    ) {
        _state.update {
            it.ClearAfterCity(
                cityId =
                    cityId
            )
        }

        if (cityId <= 0) {
            return
        }

        val currentSelection =
            _state.value.Selection

        Log.d(
            "AddressCascade",
            """
        SelectCityInternal:
        CountryId=${currentSelection.CountryId}
        CountryStateId=${currentSelection.CountryStateId}
        CountryDepartmentId=${currentSelection.CountryDepartmentId}
        CityId=$cityId
        IsTurkey=${currentSelection.IsTurkey}
        """.trimIndent()
        )

        if (!currentSelection.IsTurkey) {
            return
        }

        LoadDistrictsInternal(
            countryId =
                currentSelection.CountryId,
            countryStateId =
                currentSelection.CountryStateId,
            countryDepartmentId =
                currentSelection.CountryDepartmentId,
            cityId =
                cityId
        )

        val normalizedInitialDistrictId =
            initialDistrictId
                ?.takeIf {
                    it > 0
                }

        if (normalizedInitialDistrictId != null) {
            SelectDistrict(
                districtId =
                    normalizedInitialDistrictId
            )
        }
    }

    private suspend fun LoadDistrictsInternal(
        countryId: Int,
        countryStateId: Int,
        countryDepartmentId: Int?,
        cityId: Int
    ): List<AddressDistrictDTO> {
        _state.update {
            it.copy(
                IsDistrictsLoading =
                    true,
                DistrictError =
                    null,
                Districts =
                    emptyList()
            )
        }

        Log.d(
            "AddressCascade",
            """
            LoadDistrictsInternal:
            countryId=$countryId
            countryStateId=$countryStateId
            countryDepartmentId=$countryDepartmentId
            cityId=$cityId
            """.trimIndent()
        )

        val response =
            executeService.GetAsync(
                cacheKey =
                    buildString {
                        append("AddressCascade.Districts")
                        append(".Country.$countryId")
                        append(".State.$countryStateId")
                        append(".Department.${countryDepartmentId ?: 0}")
                        append(".City.$cityId")
                        append(".Count.$ITEM_COUNT")
                    }
            ) {
                addressDistrictRepository.GetAddressDistrictsAsync(
                    countryId =
                        countryId,
                    countryStateId =
                        countryStateId,
                    countryDepartmentId =
                        countryDepartmentId,
                    cityId =
                        cityId,
                    count =
                        ITEM_COUNT
                )
            }

        val districts: List<AddressDistrictDTO> =
            if (response.Success) {
                response.Data.orEmpty()
            } else {
                emptyList()
            }

        _state.update {
            it.copy(
                Districts =
                    districts,
                IsDistrictsLoading =
                    false,
                DistrictError =
                    if (response.Success) {
                        null
                    } else {
                        response.Message
                            ?: DEFAULT_DISTRICT_ERROR
                    }
            )
        }

        return districts
    }

    companion object {

        private const val COUNTRY_COUNT: Int =
            300

        private const val ITEM_COUNT: Int =
            10000

        private const val DEFAULT_COUNTRY_ERROR: String =
            "Ülkeler yüklenemedi."

        private const val DEFAULT_COUNTRY_STATE_ERROR: String =
            "Eyalet veya bölge listesi yüklenemedi."

        private const val DEFAULT_COUNTRY_DEPARTMENT_ERROR: String =
            "Departman listesi yüklenemedi."

        private const val DEFAULT_CITY_ERROR: String =
            "Şehirler yüklenemedi."

        private const val DEFAULT_DISTRICT_ERROR: String =
            "İlçeler yüklenemedi."
    }
}