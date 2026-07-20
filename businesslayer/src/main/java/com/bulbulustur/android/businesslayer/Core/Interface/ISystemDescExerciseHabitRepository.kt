package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescExerciseHabitDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescExerciseHabitRepository {

    suspend fun GetExerciseHabitsAsync(languageId: Int, count: Int): Result<List<SystemDescExerciseHabitDTO>>
}
