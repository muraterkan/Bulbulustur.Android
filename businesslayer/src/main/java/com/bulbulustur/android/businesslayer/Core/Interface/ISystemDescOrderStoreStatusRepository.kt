package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderStoreStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescOrderStoreStatusRepository {

    suspend fun GetSystemDescOrderStoreStatusesAsync(
        count: Int
    ): Result<List<SystemDescOrderStoreStatusDTO>>

    suspend fun GetSystemDescOrderStoreStatusByIdAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusDTO?>

    suspend fun GetSystemDescOrderStoreStatusByIdExtendedAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusDTO?>

    suspend fun InsertAsync(
        model: SystemDescOrderStoreStatusInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescOrderStoreStatusUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<Unit>
}