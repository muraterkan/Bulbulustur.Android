package com.bulbulustur.android.Application.Localization

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ILocalizationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocalizationManager(
    private val localizationRepository: ILocalizationRepository,
    private val coroutineScope: CoroutineScope
) {
    private val _State = MutableStateFlow(LocalizationState())
    val State: StateFlow<LocalizationState> = _State.asStateFlow()

    private val MemoryCache = mutableMapOf<Int, LocalizationCacheEntry>()
    private var LoadJob: Job? = null

    fun Load(languageId: Int, languageCode: String, forceRefresh: Boolean = false) {
        if (languageId <= 0) return

        val normalizedLanguageCode = languageCode.trim()

        if (!forceRefresh) {
            val cachedEntry = MemoryCache[languageId]

            if (cachedEntry != null && !cachedEntry.IsExpired()) {
                _State.value = LocalizationState(
                    IsInitialized = true,
                    IsLoading = false,
                    LanguageId = languageId,
                    LanguageCode = normalizedLanguageCode,
                    Resources = cachedEntry.Resources,
                    ErrorMessage = null
                )
                return
            }
        }

        LoadJob?.cancel()
        LoadJob = coroutineScope.launch {
            LoadFromRepository(
                languageId = languageId,
                languageCode = normalizedLanguageCode
            )
        }
    }

    fun Refresh() {
        Load(
            languageId = _State.value.LanguageId,
            languageCode = _State.value.LanguageCode,
            forceRefresh = true
        )
    }

    fun ClearMemoryCache() {
        MemoryCache.clear()
    }

    private suspend fun LoadFromRepository(languageId: Int, languageCode: String) {
        _State.value = _State.value.copy(
            IsLoading = true,
            LanguageId = languageId,
            LanguageCode = languageCode,
            ErrorMessage = null
        )

        try {
            val result = localizationRepository.GetResourcesAsync(
                languageId = languageId,
                count = ResourceCount
            )

            if (!result.Success) {
                ApplyFailure(
                    languageId = languageId,
                    languageCode = languageCode,
                    errorMessage = result.Message
                )
                return
            }

            val resources = result.Data.orEmpty().ToResourceMap()

            MemoryCache[languageId] = LocalizationCacheEntry(
                Resources = resources,
                LoadedAtMillis = System.currentTimeMillis()
            )

            _State.value = LocalizationState(
                IsInitialized = true,
                IsLoading = false,
                LanguageId = languageId,
                LanguageCode = languageCode,
                Resources = resources,
                ErrorMessage = null
            )
        } catch (exception: Exception) {
            ApplyFailure(
                languageId = languageId,
                languageCode = languageCode,
                errorMessage = exception.message ?: "Localization kaynakları yüklenemedi."
            )
        }
    }

    private fun ApplyFailure(languageId: Int, languageCode: String, errorMessage: String) {
        _State.value = LocalizationState(
            IsInitialized = true,
            IsLoading = false,
            LanguageId = languageId,
            LanguageCode = languageCode,
            Resources = MemoryCache[languageId]?.Resources.orEmpty(),
            ErrorMessage = errorMessage
        )
    }

    private fun List<ResourceDTO>.ToResourceMap(): Map<String, String> {
        return asSequence()
            .filter { it.Key.isNotBlank() }
            .associate { it.Key.trim() to it.Value }
    }

    private data class LocalizationCacheEntry(
        val Resources: Map<String, String>,
        val LoadedAtMillis: Long
    ) {
        fun IsExpired(): Boolean {
            return System.currentTimeMillis() - LoadedAtMillis >= CacheDurationMillis
        }
    }

    private companion object {
        const val ResourceCount = 10000
        const val CacheDurationMillis = 24L * 60L * 60L * 1000L
    }
}
