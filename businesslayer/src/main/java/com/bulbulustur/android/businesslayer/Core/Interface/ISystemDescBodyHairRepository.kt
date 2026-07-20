package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBodyHairDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescBodyHairRepository {

    suspend fun GetBodyHairsAsync(languageId: Int, count: Int): Result<List<SystemDescBodyHairDTO>>
}
