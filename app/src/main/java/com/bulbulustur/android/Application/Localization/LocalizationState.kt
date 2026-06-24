package com.bulbulustur.android.Application.Localization

import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage

data class LocalizationState(
    val IsInitialized: Boolean = false,
    val IsLoading: Boolean = false,
    val Language: EApplicationLanguage = EApplicationLanguage.Turkish,
    val Resources: Map<String, String> = emptyMap(),
    val ErrorMessage: String? = null
) {

    fun Get(
        key: String,
        fallback: String? = null
    ): String {
        val resourceValue = Resources[key]

        if (!resourceValue.isNullOrBlank()) {
            return resourceValue
        }

        val localFallback = LocalizationFallback.Get(
            language = Language,
            key = key
        )

        if (!localFallback.isNullOrBlank()) {
            return localFallback
        }

        if (!fallback.isNullOrBlank()) {
            return fallback
        }

        return "..."
    }
}