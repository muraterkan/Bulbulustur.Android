package com.bulbulustur.android.Application.Shared.Address

import com.bulbulustur.android.Application.Localization.BBLocalization

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

    fun OnEvent(event: AddressCascadeEvent) {
        when (event) {
            is AddressCascadeEvent.LoadCountries -> LoadCountries(event.LanguageId)

            is AddressCascadeEvent.SelectCountry -> {
                SelectCountry(
                    countryId = event.CountryId,
                    languageId = event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectCountryState -> {
                SelectCountryState(
                    countryStateId = event.CountryStateId,
                    languageId = event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectCountryDepartment -> {
                SelectCountryDepartment(
                    countryDepartmentId = event.CountryDepartmentId,
                    languageId = event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectCity -> {
                SelectCity(
                    cityId = event.CityId,
                    languageId = event.LanguageId
                )
            }

            is AddressCascadeEvent.SelectDistrict -> {
                SelectDistrict(event.DistrictId)
            }

            is AddressCascadeEvent.SetInitialSelection -> {
                SetInitialSelection(
                    selection = event.Selection,
                    languageId = event.LanguageId
                )
            }

            AddressCascadeEvent.Clear -> Clear()
            AddressCascadeEvent.ClearErrors -> ClearErrors()
        }
    }

    fun LoadCountries(languageId: Int) {
        viewModelScope.launch {
            LoadCountriesInternal(languageId)
        }
    }

    fun SelectCountry(countryId: Int, languageId: Int) {
        viewModelScope.launch {
            SelectCountryInternal(
                countryId = countryId,
                languageId = languageId
            )
        }
    }

    fun SelectCountryState(countryStateId: Int, languageId: Int) {
        viewModelScope.launch {
            SelectCountryStateInternal(
                countryStateId = countryStateId,
                languageId = languageId
            )
        }
    }

    fun SelectCountryDepartment(countryDepartmentId: Int?, languageId: Int) {
        viewModelScope.launch {
            SelectCountryDepartmentInternal(
                countryDepartmentId = countryDepartmentId,
                languageId = languageId
            )
        }
    }

    fun SelectCity(cityId: Int, languageId: Int) {
        viewModelScope.launch {
            SelectCityInternal(
                cityId = cityId,
                languageId = languageId
            )
        }
    }

    fun SelectDistrict(districtId: Int?) {
        val normalizedDistrictId =
            districtId?.takeIf {
                    selectedDistrictId ->
                _state.value.Districts.any {
                    it.AddressDistrictId == selectedDistrictId
                }
            }

        _state.update {
            it.copy(
                Selection =
                    it.Selection.copy(
                        DistrictId =
                            normalizedDistrictId
                    ),
                DistrictError =
                    null
            )
        }
    }

    fun SetInitialSelection(selection: AddressCascadeSelection, languageId: Int) {
        viewModelScope.launch {
            val normalizedSelection =
                selection.Normalize()

            LoadCountriesInternal(languageId)

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
                countryId = normalizedSelection.CountryId,
                languageId = languageId,
                initialSelection = normalizedSelection
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

    private suspend fun LoadCountriesInternal(languageId: Int): List<AddressCountryDTO> {
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
                    languageId = languageId,
                    count = COUNTRY_COUNT
                )
            }

        val countries =
            if (response.Success) {
                response.Data
                    .orEmpty()
                    .filter {
                        it.AddressCountryId > 0 &&
                                it.Content.isNotBlank()
                    }
                    .sortedWith(
                        compareBy<AddressCountryDTO> {
                            it.DisplayOrder
                                ?: Int.MAX_VALUE
                        }.thenBy {
                            it.Content
                        }
                    )
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
                    when {
                        !response.Success ->
                            response.Message
                                ?: DEFAULT_COUNTRY_ERROR

                        countries.isEmpty() ->
                            BBLocalization.Current.Get(key = "8e923637-2574-47be-837e-30dde5e39a31", fallback = "Ülke verisi bulunamadı.")

                        else ->
                            null
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

        val selectedCountryExists =
            _state.value.Countries.any {
                it.AddressCountryId == countryId
            }

        if (!selectedCountryExists) {
            _state.update {
                it.ClearCountrySelection().copy(
                    CountryError =
                        BBLocalization.Current.Get(key = "d3b3cf06-338f-4566-afb6-e27ae355b2b2", fallback = "Seçilen ülke bulunamadı.")
                )
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
            LoadCitiesByCountryInternal(
                countryId = countryId
            )

            SelectInitialCityIfAvailable(
                initialSelection = initialSelection,
                languageId = languageId
            )

            return
        }

        val initialCountryStateId =
            initialSelection
                ?.CountryStateId
                ?.takeIf { countryStateId ->
                    countryStates.any {
                        it.AddressCountryStateId == countryStateId
                    }
                }

        val targetCountryStateId =
            initialCountryStateId
                ?: countryStates
                    .singleOrNull()
                    ?.AddressCountryStateId

        if (targetCountryStateId != null) {
            SelectCountryStateInternal(
                countryStateId = targetCountryStateId,
                languageId = languageId,
                initialSelection = initialSelection
            )
        }
    }

    private suspend fun LoadCountryStatesInternal(countryId: Int): List<AddressCountryStateDTO> {
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
                addressCountryStateRepository.GetAddressCountryStatesAsync(
                    countryId = countryId,
                    count = ITEM_COUNT
                )
            }

        val countryStates =
            if (response.Success) {
                response.Data
                    .orEmpty()
                    .filter {
                        it.AddressCountryStateId > 0 &&
                                it.CountryId == countryId &&
                                it.StateName.isNotBlank() &&
                                it.HasAdministration
                    }
                    .sortedWith(
                        compareBy<AddressCountryStateDTO> {
                            it.DisplayOrder
                                ?: Int.MAX_VALUE
                        }.thenBy {
                            it.StateName
                        }
                    )
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

        if (currentCountryId <= 0 || countryStateId <= 0) {
            _state.update {
                it.ClearAfterCountryState(
                    countryStateId =
                        0
                )
            }

            return
        }

        val selectedCountryStateExists =
            _state.value.CountryStates.any {
                it.AddressCountryStateId == countryStateId &&
                        it.CountryId == currentCountryId
            }

        if (!selectedCountryStateExists) {
            _state.update {
                it.ClearAfterCountryState(
                    countryStateId =
                        0
                ).copy(
                    CountryStateError =
                        BBLocalization.Current.Get(key = "93919901-73b4-4131-bb2c-a422c7388b0f", fallback = "Seçilen state veya bölge bu ülkeye ait değil.")
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
                countryId = currentCountryId,
                countryStateId = countryStateId
            )

        if (countryDepartments.isEmpty()) {
            LoadCitiesInternal(
                languageId = languageId,
                countryId = currentCountryId,
                countryStateId = countryStateId,
                countryDepartmentId = null
            )

            SelectInitialCityIfAvailable(
                initialSelection = initialSelection,
                languageId = languageId
            )

            return
        }

        val initialCountryDepartmentId =
            initialSelection
                ?.CountryDepartmentId
                ?.takeIf { departmentId ->
                    countryDepartments.any {
                        it.AddressCountryDepartmentId == departmentId
                    }
                }

        val targetCountryDepartmentId =
            initialCountryDepartmentId
                ?: countryDepartments
                    .singleOrNull()
                    ?.AddressCountryDepartmentId

        if (targetCountryDepartmentId != null) {
            SelectCountryDepartmentInternal(
                countryDepartmentId = targetCountryDepartmentId,
                languageId = languageId,
                initialSelection = initialSelection
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
                addressCountryDepartmentRepository.GetAddressCountryDepartmentsAsync(
                    countryId = countryId,
                    countryStateId = countryStateId,
                    count = ITEM_COUNT
                )
            }

        val countryDepartments =
            if (response.Success) {
                response.Data
                    .orEmpty()
                    .filter {
                        it.AddressCountryDepartmentId > 0 &&
                                it.CountryId == countryId &&
                                it.StateId == countryStateId &&
                                it.DepartmentName.isNotBlank()
                    }
                    .sortedBy {
                        it.DepartmentName
                    }
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

        if (!currentSelection.HasCountry || !currentSelection.HasCountryState) {
            return
        }

        val normalizedCountryDepartmentId =
            countryDepartmentId
                ?.takeIf { departmentId ->
                    _state.value.CountryDepartments.any {
                        it.AddressCountryDepartmentId == departmentId &&
                                it.CountryId == currentSelection.CountryId &&
                                it.StateId == currentSelection.CountryStateId
                    }
                }

        if (
            countryDepartmentId != null &&
            countryDepartmentId > 0 &&
            normalizedCountryDepartmentId == null
        ) {
            _state.update {
                it.copy(
                    CountryDepartmentError =
                        BBLocalization.Current.Get(key = "ba0dbb42-1c15-447e-aa54-bf7e7902411f", fallback = "Seçilen departman bu state veya bölgeye ait değil.")
                )
            }

            return
        }

        _state.update {
            it.ClearAfterCountryDepartment(
                countryDepartmentId =
                    normalizedCountryDepartmentId
            )
        }

        LoadCitiesInternal(
            languageId = languageId,
            countryId = currentSelection.CountryId,
            countryStateId = currentSelection.CountryStateId,
            countryDepartmentId = normalizedCountryDepartmentId
        )

        SelectInitialCityIfAvailable(
            initialSelection = initialSelection,
            languageId = languageId
        )
    }
    private suspend fun LoadCitiesByCountryInternal(
        countryId: Int
    ): List<AddressCityDTO> {
        if (countryId <= 0) {
            return emptyList()
        }

        _state.update {
            it.copy(
                IsCitiesLoading = true,
                CityError = null,
                Cities = emptyList(),
                Districts = emptyList()
            )
        }

        val response =
            executeService.GetAsync(
                cacheKey =
                    "AddressCascade.Cities.Country.$countryId.Count.$ITEM_COUNT"
            ) {
                addressCityRepository.GetAddressCitiesAsync(
                    countryId = countryId,
                    count = ITEM_COUNT
                )
            }

        val cities =
            if (response.Success) {
                response.Data
                    .orEmpty()
                    .filter {
                        it.AddressCityId > 0 &&
                                it.CountryId == countryId &&
                                it.Content.isNotBlank()
                    }
                    .sortedWith(
                        compareBy<AddressCityDTO> {
                            it.DisplayOrder ?: Int.MAX_VALUE
                        }.thenBy {
                            it.Content
                        }
                    )
            } else {
                emptyList()
            }

        _state.update {
            it.copy(
                Cities = cities,
                IsCitiesLoading = false,
                CityError =
                    when {
                        !response.Success ->
                            response.Message ?: DEFAULT_CITY_ERROR

                        cities.isEmpty() ->
                            BBLocalization.Current.Get(key = "98c9713e-7c0d-4065-b7ef-14b59014cd3e", fallback = "Bu ülke için şehir bulunamadı.")

                        else ->
                            null
                    }
            )
        }

        return cities
    }

    private suspend fun LoadCitiesInternal(
        languageId: Int,
        countryId: Int,
        countryStateId: Int,
        countryDepartmentId: Int?
    ): List<AddressCityDTO> {
        if (countryId <= 0 || countryStateId <= 0) {
            _state.update {
                it.copy(
                    Cities =
                        emptyList(),
                    Districts =
                        emptyList(),
                    CityError =
                        BBLocalization.Current.Get(key = "58b8f8dd-e1c7-477a-a742-404feb9440fc", fallback = "Şehir yüklemek için ülke ve state seçilmelidir.")
                )
            }

            return emptyList()
        }

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
                    languageId = languageId,
                    countryId = countryId,
                    countryStateId = countryStateId,
                    countryDepartmentId = countryDepartmentId,
                    count = ITEM_COUNT
                )
            }

        val cities =
            if (response.Success) {
                response.Data
                    .orEmpty()
                    .filter {
                        it.AddressCityId > 0 &&
                                it.CountryId == countryId &&
                                it.CountryStateId == countryStateId &&
                                (
                                        countryDepartmentId == null ||
                                                it.CountryDepartmentId == countryDepartmentId
                                        ) &&
                                it.Content.isNotBlank()
                    }
                    .sortedWith(
                        compareBy<AddressCityDTO> {
                            it.DisplayOrder
                                ?: Int.MAX_VALUE
                        }.thenBy {
                            it.Content
                        }
                    )
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
                    when {
                        !response.Success ->
                            response.Message
                                ?: DEFAULT_CITY_ERROR

                        cities.isEmpty() ->
                            BBLocalization.Current.Get(key = "305fc4be-2a2b-4229-a032-0b12b27d27b7", fallback = "Seçilen coğrafi zincir için şehir bulunamadı.")

                        else ->
                            null
                    }
            )
        }

        return cities
    }

    private suspend fun SelectInitialCityIfAvailable(
        initialSelection: AddressCascadeSelection?,
        languageId: Int
    ) {
        val initialCityId =
            initialSelection
                ?.CityId
                ?.takeIf { cityId ->
                    _state.value.Cities.any {
                        it.AddressCityId == cityId
                    }
                }
                ?: return

        SelectCityInternal(
            cityId = initialCityId,
            languageId = languageId,
            initialDistrictId = initialSelection.DistrictId
        )
    }

    private suspend fun SelectCityInternal(
        cityId: Int,
        languageId: Int,
        initialDistrictId: Int? = null
    ) {
        if (cityId <= 0) {
            _state.update {
                it.ClearAfterCity(
                    cityId =
                        0
                )
            }

            return
        }

        val currentSelection =
            _state.value.Selection

        val selectedCity =
            _state.value.Cities.firstOrNull {
                it.AddressCityId == cityId &&
                        it.CountryId == currentSelection.CountryId &&
                        (
                                !currentSelection.HasCountryState ||
                                        it.CountryStateId == currentSelection.CountryStateId
                                ) &&
                        (
                                currentSelection.CountryDepartmentId == null ||
                                        it.CountryDepartmentId == currentSelection.CountryDepartmentId
                                )
            }

        if (selectedCity == null) {
            _state.update {
                it.ClearAfterCity(
                    cityId =
                        0
                ).copy(
                    CityError =
                        BBLocalization.Current.Get(key = "9df7df54-7d3b-466e-851d-d3ad059bb69a", fallback = "Seçilen şehir mevcut coğrafi zincire ait değil.")
                )
            }

            return
        }

        _state.update {
            it.ClearAfterCity(
                cityId =
                    selectedCity.AddressCityId
            ).copy(
                Selection =
                    it.Selection.copy(
                        CountryId =
                            selectedCity.CountryId,
                        CountryStateId =
                            selectedCity.CountryStateId,
                        CountryDepartmentId =
                            selectedCity.CountryDepartmentId
                                ?.takeIf {
                                        value ->
                                    value > 0
                                },
                        CityId =
                            selectedCity.AddressCityId,
                        DistrictId =
                            null
                    ),
                CityError =
                    null
            )
        }

        val selectedLocation =
            _state.value.Selection

        LoadDistrictsInternal(
            countryId = selectedLocation.CountryId,
            countryStateId = selectedLocation.CountryStateId,
            countryDepartmentId = selectedLocation.CountryDepartmentId,
            cityId = selectedLocation.CityId
        )

        val normalizedInitialDistrictId =
            initialDistrictId
                ?.takeIf { districtId ->
                    _state.value.Districts.any {
                        it.AddressDistrictId == districtId
                    }
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
                    countryId = countryId,
                    countryStateId = countryStateId,
                    countryDepartmentId = countryDepartmentId,
                    cityId = cityId,
                    count = ITEM_COUNT
                )
            }

        val districts =
            if (response.Success) {
                response.Data
                    .orEmpty()
                    .filter {
                        it.AddressDistrictId > 0 &&
                                it.CountryId == countryId &&
                                it.CityId == cityId &&
                                !it.Content.isNullOrBlank()
                    }
                    .sortedBy {
                        it.Content
                    }
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

        private val COUNTRY_COUNT: Int =
            300

        private val ITEM_COUNT: Int =
            10000

        private val DEFAULT_COUNTRY_ERROR: String =
            BBLocalization.Current.Get(key = "ee41820b-49bb-4918-a52c-6a2d8da76c56", fallback = "Ülkeler yüklenemedi.")

        private val DEFAULT_COUNTRY_STATE_ERROR: String =
            BBLocalization.Current.Get(key = "1e608c83-f6da-4099-a5ff-27630c4c7fb9", fallback = "Eyalet veya bölge listesi yüklenemedi.")

        private val DEFAULT_COUNTRY_DEPARTMENT_ERROR: String =
            BBLocalization.Current.Get(key = "a4d204cd-6744-466f-b136-ea7dad3f4a12", fallback = "Departman listesi yüklenemedi.")

        private val DEFAULT_CITY_ERROR: String =
            BBLocalization.Current.Get(key = "c72a9644-9dbe-4f63-b9c2-82dac9717601", fallback = "Şehirler yüklenemedi.")

        private val DEFAULT_DISTRICT_ERROR: String =
            BBLocalization.Current.Get(key = "8eb58a9b-7e21-4a3a-9b1d-675b03410483", fallback = "İlçeler yüklenemedi.")
    }
}