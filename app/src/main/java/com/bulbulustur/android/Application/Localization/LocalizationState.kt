package com.bulbulustur.android.Application.Localization

data class LocalizationState(
    val IsInitialized: Boolean = false,
    val IsLoading: Boolean = false,
    val LanguageId: Int = 1,
    val LanguageCode: String = "tr",
    val Resources: Map<String, String> = emptyMap(),
    val ErrorMessage: String? = null
) {
    fun Get(key: String, fallback: String? = null): String {
        val resourceValue = Resources[key]
        if (!resourceValue.isNullOrBlank()) return resourceValue
        if (!fallback.isNullOrBlank()) return fallback
        return "..."
    }
}
