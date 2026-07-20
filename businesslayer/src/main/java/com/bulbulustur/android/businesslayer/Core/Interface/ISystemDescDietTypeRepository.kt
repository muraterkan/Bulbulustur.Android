package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescDietTypeDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescDietTypeRepository {

    suspend fun GetDietTypesAsync(languageId: Int, count: Int): Result<List<SystemDescDietTypeDTO>>
}
