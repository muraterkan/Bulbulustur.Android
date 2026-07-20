package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescDietTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescDietTypeRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescDietTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescDietTypeRepository {

    override suspend fun GetDietTypesAsync(languageId: Int, count: Int): Result<List<SystemDescDietTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescDietTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
