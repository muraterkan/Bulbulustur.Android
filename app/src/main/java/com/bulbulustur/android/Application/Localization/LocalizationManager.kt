package com.bulbulustur.android.Application.Localization

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import com.bulbulustur.android.businesslayer.Core.Interface.ILocalizationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocalizationManager(
    private val localizationRepository: ILocalizationRepository,
    private val coroutineScope: CoroutineScope
) {

    private val _State = MutableStateFlow(
        LocalizationState()
    )

    val State: StateFlow<LocalizationState> =
        _State.asStateFlow()

    private val MemoryCache:
            MutableMap<EApplicationLanguage, LocalizationCacheEntry> =
        mutableMapOf()

    private var LoadJob: Job? = null

    fun Load(
        language: EApplicationLanguage,
        forceRefresh: Boolean = false
    ) {
        if (!forceRefresh) {
            val cachedEntry = MemoryCache[language]

            if (cachedEntry != null && !cachedEntry.IsExpired()) {
                _State.value = LocalizationState(
                    IsInitialized = true,
                    IsLoading = false,
                    Language = language,
                    Resources = cachedEntry.Resources,
                    ErrorMessage = null
                )

                return
            }
        }

        LoadJob?.cancel()

        LoadJob = coroutineScope.launch {
            LoadFromRepository(
                language = language
            )
        }
    }

    fun Refresh() {
        Load(
            language = _State.value.Language,
            forceRefresh = true
        )
    }

    fun ClearMemoryCache() {
        MemoryCache.clear()
    }

    private suspend fun LoadFromRepository(
        language: EApplicationLanguage
    ) {
        val previousState = _State.value

        _State.value = previousState.copy(
            IsLoading = true,
            Language = language,
            ErrorMessage = null
        )

        try {
            val result = localizationRepository.GetResourcesAsync(
                language = language,
                count = ResourceCount
            )

            if (!result.Success) {
                ApplyFailure(
                    language = language,
                    errorMessage = result.Message
                )

                return
            }

            val resources = result.Data
                .orEmpty()
                .ToResourceMap()

            MemoryCache[language] = LocalizationCacheEntry(
                Resources = resources,
                LoadedAtMillis = System.currentTimeMillis()
            )

            _State.value = LocalizationState(
                IsInitialized = true,
                IsLoading = false,
                Language = language,
                Resources = resources,
                ErrorMessage = null
            )
        } catch (exception: Exception) {
            ApplyFailure(
                language = language,
                errorMessage = exception.message
                    ?: "Localization kaynakları yüklenemedi."
            )
        }
    }

    private fun ApplyFailure(
        language: EApplicationLanguage,
        errorMessage: String
    ) {
        val cachedResources = MemoryCache[language]
            ?.Resources
            .orEmpty()

        _State.value = LocalizationState(
            IsInitialized = true,
            IsLoading = false,
            Language = language,
            Resources = cachedResources,
            ErrorMessage = errorMessage
        )
    }

    private fun List<ResourceDTO>.ToResourceMap(): Map<String, String> {
        return asSequence()
            .filter { resource ->
                resource.Key.isNotBlank()
            }
            .associate { resource ->
                resource.Key.trim() to resource.Value
            }
    }

    private data class LocalizationCacheEntry(
        val Resources: Map<String, String>,
        val LoadedAtMillis: Long
    ) {

        fun IsExpired(): Boolean {
            val ageMillis =
                System.currentTimeMillis() - LoadedAtMillis

            return ageMillis >= CacheDurationMillis
        }
    }

    private companion object {

        const val ResourceCount = 10000

        const val CacheDurationMillis =
            24L * 60L * 60L * 1000L
    }
}