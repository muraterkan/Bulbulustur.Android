package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReligionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescReligionRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescReligionRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescReligionRepository {

    override suspend fun GetReligionsAsync(languageId: Int, count: Int): Result<List<SystemDescReligionDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescReligionsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
