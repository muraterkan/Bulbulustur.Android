package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescExerciseHabitDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescExerciseHabitRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescExerciseHabitRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescExerciseHabitRepository {

    override suspend fun GetExerciseHabitsAsync(languageId: Int, count: Int): Result<List<SystemDescExerciseHabitDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescExerciseHabitsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }
}
