package com.bulbulustur.android.Core.Repository

import com.bulbulustur.android.Core.Network.ApiClient

class LocalizationRepository {

    suspend fun getResourceValue(
        languageId: Int,
        key: String
    ): String {
        return try {
            val response = ApiClient.localizationApiService.getResourcesAsync(
                languageId = languageId,
                count = 10000
            )

            response.data
                ?.firstOrNull { it.key == key }
                ?.value
                ?: "..."
        } catch (_: Exception) {
            "..."
        }
    }
}