package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreLineStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreLineStatusUpdateModel

interface ISystemDescOrderStoreLineStatusRepository {

    suspend fun GetSystemDescOrderStoreLineStatusListAsync(): Result<List<SystemDescOrderStoreLineStatusDTO>>

    suspend fun GetSystemDescOrderStoreLineStatusByIdAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusUpdateModel?>

    suspend fun GetSystemDescOrderStoreLineStatusByIdExtendedAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusDTO?>
}
