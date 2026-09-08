package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNotificationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescNotificationTypeRepository {
    suspend fun GetNotificationTypesAsync(
        languageId: Int,
        count: Int = 100
    ): Result<List<SystemDescNotificationTypeDTO>>
}
