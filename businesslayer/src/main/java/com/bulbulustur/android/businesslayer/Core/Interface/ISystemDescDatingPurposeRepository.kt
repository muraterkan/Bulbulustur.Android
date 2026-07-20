package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescDatingPurposeDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescDatingPurposeRepository {

    suspend fun GetDatingPurposesAsync(languageId: Int, count: Int): Result<List<SystemDescDatingPurposeDTO>>
}
