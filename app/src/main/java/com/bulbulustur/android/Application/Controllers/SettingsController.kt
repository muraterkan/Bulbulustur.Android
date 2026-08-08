package com.bulbulustur.android.Application.Controllers

import com.bulbulustur.android.Application.Localization.BBLocalization

import android.content.Context
import androidx.lifecycle.viewModelScope
import coil3.imageLoader
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.StatusOverviewDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IStatusRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCurrencyRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SettingsControllerState(
    val IsLoadingLanguages: Boolean = false,
    val IsLoadingCountries: Boolean = false,
    val IsLoadingCurrencies: Boolean = false,
    val IsLoadingStatus: Boolean = false,
    val IsClearingCache: Boolean = false,
    val IsCacheCleared: Boolean = false,
    val CurrentAction: String? = null,
    val Languages: List<SystemDescLanguageDTO> = emptyList(),
    val Countries: List<AddressCountryDTO> = emptyList(),
    val Currencies: List<SystemDescCurrencyDTO> = emptyList(),
    val StatusOverview: StatusOverviewDTO? = null,
    val LanguageResult: Result<List<SystemDescLanguageDTO>>? = null,
    val CountryResult: Result<List<AddressCountryDTO>>? = null,
    val CurrencyResult: Result<List<SystemDescCurrencyDTO>>? = null,
    val StatusOverviewResult: Result<StatusOverviewDTO?>? = null,
    val CacheMessage: String? = null,
    val ErrorMessage: String? = null
)

class SettingsController(
    private val executeService: IExecuteService,
    private val systemDescLanguageRepository: ISystemDescLanguageRepository,
    private val addressCountryRepository: IAddressCountryRepository,
    private val systemDescCurrencyRepository: ISystemDescCurrencyRepository,
    private val statusRepository: IStatusRepository
) : BaseController() {

    private val _state = MutableStateFlow(SettingsControllerState())

    val State: StateFlow<SettingsControllerState> = _state.asStateFlow()

    fun GetLanguages(languageId: Int, count: Int = 100) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsLoadingLanguages = true,
                    CurrentAction = "GetLanguages",
                    ErrorMessage = null
                )
            }

            val response = executeService.GetAsync(
                cacheKey = ""
            ) {
                systemDescLanguageRepository.GetSystemDescLanguagesAsync(
                    languageId = languageId,
                    count = count
                )
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoadingLanguages = false,
                    Languages = response.Data.orEmpty(),
                    LanguageResult = response,
                    ErrorMessage = if (response.Success) null else response.Message
                )
            }
        }
    }

    fun GetCountries(languageId: Int, count: Int = 300) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsLoadingCountries = true,
                    CurrentAction = "GetCountries",
                    ErrorMessage = null
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "Settings.Countries.$languageId.$count"
            ) {
                addressCountryRepository.GetAddressCountriesAsync(
                    languageId = languageId,
                    count = count
                )
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoadingCountries = false,
                    Countries = response.Data.orEmpty(),
                    CountryResult = response,
                    ErrorMessage = if (response.Success) null else response.Message
                )
            }
        }
    }

    fun GetCurrencies(languageId: Int, count: Int = 100) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsLoadingCurrencies = true,
                    CurrentAction = "GetCurrencies",
                    ErrorMessage = null
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "Settings.Currencies.$languageId.$count"
            ) {
                systemDescCurrencyRepository.GetSystemDescCurrenciesAsync(
                    languageId = languageId,
                    count = count
                )
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoadingCurrencies = false,
                    Currencies = response.Data.orEmpty(),
                    CurrencyResult = response,
                    ErrorMessage = if (response.Success) null else response.Message
                )
            }
        }
    }

    fun GetStatusOverview() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsLoadingStatus = true,
                    CurrentAction = "GetStatusOverview",
                    ErrorMessage = null
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "Settings.StatusOverview"
            ) {
                statusRepository.GetOverviewAsync()
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoadingStatus = false,
                    StatusOverview = response.Data,
                    StatusOverviewResult = response,
                    ErrorMessage = if (response.Success) null else response.Message
                )
            }
        }
    }

    fun RefreshLanguages(languageId: Int, count: Int = 100) {
        GetLanguages(languageId = languageId, count = count)
    }

    fun RefreshCountries(languageId: Int, count: Int = 300) {
        GetCountries(languageId = languageId, count = count)
    }

    fun RefreshCurrencies(languageId: Int, count: Int = 100) {
        GetCurrencies(languageId = languageId, count = count)
    }

    fun RefreshStatusOverview() {
        GetStatusOverview()
    }

    fun ClearApplicationCache(context: Context) {
        if (_state.value.IsClearingCache) return

        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsClearingCache = true,
                    IsCacheCleared = false,
                    CurrentAction = "ClearApplicationCache",
                    CacheMessage = null,
                    ErrorMessage = null
                )
            }

            val result = runCatching {
                executeService.ClearMemoryCache()

                context.imageLoader.memoryCache?.clear()
                context.imageLoader.diskCache?.clear()

                context.cacheDir.listFiles().orEmpty().forEach { cacheFile ->
                    cacheFile.deleteRecursively()
                }

                context.externalCacheDir?.listFiles().orEmpty().forEach { cacheFile ->
                    cacheFile.deleteRecursively()
                }
            }

            _state.update { currentState ->
                currentState.copy(
                    IsClearingCache = false,
                    IsCacheCleared = result.isSuccess,
                    CacheMessage = if (result.isSuccess) {
                        BBLocalization.Current.Get(key = "e305d8cc-bdde-4c75-9134-abc88a9ed23b", fallback = "Önbellek başarıyla temizlendi.")
                    } else {
                        BBLocalization.Current.Get(key = "900d0aa2-f733-462c-a4b6-0e627487ca6d", fallback = "Önbellek tamamen temizlenemedi.")
                    },
                    ErrorMessage = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun ClearError() {
        _state.update { currentState ->
            currentState.copy(
                ErrorMessage = null
            )
        }
    }
}