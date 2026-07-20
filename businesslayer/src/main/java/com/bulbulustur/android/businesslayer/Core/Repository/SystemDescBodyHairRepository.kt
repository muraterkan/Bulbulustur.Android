package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBodyHairDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBodyHairRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescBodyHairRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescBodyHairRepository {

    override suspend fun GetBodyHairsAsync(languageId: Int, count: Int): Result<List<SystemDescBodyHairDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescBodyHairsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
