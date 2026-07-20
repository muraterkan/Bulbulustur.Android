package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSkinToneDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescSkinToneRepository {

    suspend fun GetSkinTonesAsync(languageId: Int, count: Int): Result<List<SystemDescSkinToneDTO>>
}
