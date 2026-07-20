package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSmokingHabitDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSmokingHabitRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescSmokingHabitRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescSmokingHabitRepository {

    override suspend fun GetSmokingHabitsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescSmokingHabitDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescSmokingHabitsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
