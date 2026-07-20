package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescDatingPurposeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescDatingPurposeRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescDatingPurposeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescDatingPurposeRepository {

    override suspend fun GetDatingPurposesAsync(languageId: Int, count: Int): Result<List<SystemDescDatingPurposeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescDatingPurposesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
