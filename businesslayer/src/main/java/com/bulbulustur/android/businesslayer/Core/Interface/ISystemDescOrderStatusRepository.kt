package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStatusUpdateModel

interface ISystemDescOrderStatusRepository {

    suspend fun GetSystemDescOrderStatusListAsync(): Result<List<SystemDescOrderStatusDTO>>

    suspend fun GetSystemDescOrderStatusByIdAsync(
        systemDescOrderStatusId: Int
    ): Result<SystemDescOrderStatusUpdateModel?>

    suspend fun GetSystemDescOrderStatusByIdExtendedAsync(
        systemDescOrderStatusId: Int
    ): Result<SystemDescOrderStatusDTO?>
}
