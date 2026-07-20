package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRelationshipTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescRelationshipTypeRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRelationshipTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRelationshipTypeRepository {

    override suspend fun GetRelationshipTypesAsync(languageId: Int, count: Int): Result<List<SystemDescRelationshipTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRelationshipTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
