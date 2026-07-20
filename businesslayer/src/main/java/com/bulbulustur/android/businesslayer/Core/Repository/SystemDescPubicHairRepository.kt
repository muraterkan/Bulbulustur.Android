package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPubicHairDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPubicHairRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPubicHairRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPubicHairRepository {

    override suspend fun GetPubicHairsAsync(languageId: Int, count: Int): Result<List<SystemDescPubicHairDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescPubicHairsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
