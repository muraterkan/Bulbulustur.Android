package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreStatusUpdateModel

interface ISystemDescOrderStoreStatusRepository {

    suspend fun GetSystemDescOrderStoreStatusListAsync(): Result<List<SystemDescOrderStoreStatusDTO>>

    suspend fun GetSystemDescOrderStoreStatusByIdAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusUpdateModel?>

    suspend fun GetSystemDescOrderStoreStatusByIdExtendedAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusDTO?>
}
