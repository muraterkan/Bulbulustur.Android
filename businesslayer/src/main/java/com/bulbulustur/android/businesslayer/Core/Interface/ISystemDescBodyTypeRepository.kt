package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBodyTypeDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescBodyTypeRepository {

    suspend fun GetBodyTypesAsync(languageId: Int, count: Int): Result<List<SystemDescBodyTypeDTO>>
}
