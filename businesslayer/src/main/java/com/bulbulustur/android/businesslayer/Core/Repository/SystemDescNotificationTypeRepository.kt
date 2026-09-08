package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNotificationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescNotificationTypeRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescNotificationTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescNotificationTypeRepository {

    override suspend fun GetNotificationTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescNotificationTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescNotificationTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
