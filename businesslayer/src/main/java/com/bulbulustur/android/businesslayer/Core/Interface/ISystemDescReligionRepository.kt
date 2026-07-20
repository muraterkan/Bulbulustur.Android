package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReligionDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescReligionRepository {

    suspend fun GetReligionsAsync(languageId: Int, count: Int): Result<List<SystemDescReligionDTO>>
}
