package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescEducationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescEducationRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescEducationRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescEducationRepository {

    override suspend fun GetEducationsAsync(languageId: Int, count: Int): Result<List<SystemDescEducationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescEducationsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
