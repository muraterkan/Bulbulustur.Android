package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAlcoholHabitDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescAlcoholHabitRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescAlcoholHabitRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescAlcoholHabitRepository {

    override suspend fun GetAlcoholHabitsAsync(languageId: Int, count: Int): Result<List<SystemDescAlcoholHabitDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescAlcoholHabitsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
