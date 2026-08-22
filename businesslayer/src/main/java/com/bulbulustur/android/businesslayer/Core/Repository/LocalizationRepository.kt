package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ILocalizationRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Util.ErrorType
import com.bulbulustur.android.businesslayer.Core.Util.Result
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class LocalizationRepository : ILocalizationRepository {

    override suspend fun GetResourcesAsync(
        languageId: Int,
        count: Int
    ): Result<List<ResourceDTO>> {
        return try {
            val safeCount = count.coerceIn(
                minimumValue = 1,
                maximumValue = 10000
            )

            val query = BuildQuery(
                languageId = languageId,
                count = safeCount
            )

            ApiClient.GetAsync<List<ResourceDTO>>(
                baseUrl = ResourceApiBaseUrl,
                method = GetResourcesMethod,
                query = query
            )
        } catch (exception: Exception) {
            Result(
                Success = false,
                Message = "Localization kaynakları alınamadı.",
                ErrorType = ErrorType.Exception,
                Exception = exception.message,
                Data = emptyList()
            )
        }
    }

    private fun BuildQuery(
        languageId: Int,
        count: Int
    ): String {
        return listOf(
            "languageId" to languageId.toString(),
            "count" to count.toString()
        ).joinToString("&") { (key, value) ->
            "${Encode(key)}=${Encode(value)}"
        }
    }

    private fun Encode(
        value: String
    ): String {
        return URLEncoder.encode(
            value,
            StandardCharsets.UTF_8.toString()
        )
    }

    private companion object {

        const val ResourceApiBaseUrl =
            "http://77.92.144.197:30215/api/Resource"

        const val GetResourcesMethod =
            "GetResourcesAsync"
    }
}