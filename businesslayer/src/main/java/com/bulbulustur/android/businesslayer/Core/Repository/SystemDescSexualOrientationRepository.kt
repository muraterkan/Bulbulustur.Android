package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSexualOrientationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSexualOrientationRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescSexualOrientationRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescSexualOrientationRepository {

    override suspend fun GetSexualOrientationsAsync(languageId: Int, count: Int): Result<List<SystemDescSexualOrientationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescSexualOrientationsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
