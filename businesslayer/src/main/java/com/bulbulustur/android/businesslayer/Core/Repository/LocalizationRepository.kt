/*package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

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
}*/