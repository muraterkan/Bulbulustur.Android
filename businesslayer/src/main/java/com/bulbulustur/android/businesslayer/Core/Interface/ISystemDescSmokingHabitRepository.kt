package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSmokingHabitDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescSmokingHabitRepository {

    suspend fun GetSmokingHabitsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescSmokingHabitDTO>>
}
