package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBodyTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBodyTypeRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescBodyTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescBodyTypeRepository {

    override suspend fun GetBodyTypesAsync(languageId: Int, count: Int): Result<List<SystemDescBodyTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescBodyTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
