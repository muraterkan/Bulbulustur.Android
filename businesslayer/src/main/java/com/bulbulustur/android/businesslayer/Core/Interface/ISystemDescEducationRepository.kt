package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescEducationDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescEducationRepository {

    suspend fun GetEducationsAsync(languageId: Int, count: Int): Result<List<SystemDescEducationDTO>>
}
