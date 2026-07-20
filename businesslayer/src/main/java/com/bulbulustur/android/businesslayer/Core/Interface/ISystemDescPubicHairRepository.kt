package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPubicHairDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescPubicHairRepository {

    suspend fun GetPubicHairsAsync(languageId: Int, count: Int): Result<List<SystemDescPubicHairDTO>>
}
