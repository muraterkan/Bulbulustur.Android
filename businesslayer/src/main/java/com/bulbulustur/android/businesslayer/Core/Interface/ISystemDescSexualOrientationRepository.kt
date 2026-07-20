package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSexualOrientationDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescSexualOrientationRepository {

    suspend fun GetSexualOrientationsAsync(languageId: Int, count: Int): Result<List<SystemDescSexualOrientationDTO>>
}
