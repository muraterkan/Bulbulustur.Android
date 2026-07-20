package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAlcoholHabitDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescAlcoholHabitRepository {

    suspend fun GetAlcoholHabitsAsync(languageId: Int, count: Int): Result<List<SystemDescAlcoholHabitDTO>>
}
