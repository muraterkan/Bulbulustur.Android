package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSkinToneDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSkinToneRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescSkinToneRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescSkinToneRepository {

    override suspend fun GetSkinTonesAsync(languageId: Int, count: Int): Result<List<SystemDescSkinToneDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescSkinTonesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
