package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescChildrenPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescChildrenPreferenceRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescChildrenPreferenceRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescChildrenPreferenceRepository {

    override suspend fun GetChildrenPreferencesAsync(languageId: Int, count: Int): Result<List<SystemDescChildrenPreferenceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescChildrenPreferencesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
