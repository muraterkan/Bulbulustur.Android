package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreLineStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderStoreLineStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreLineStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescOrderStoreLineStatusRepository {

    suspend fun GetSystemDescOrderStoreLineStatusesAsync(
        count: Int
    ): Result<List<SystemDescOrderStoreLineStatusDTO>>

    suspend fun GetSystemDescOrderStoreLineStatusByIdAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusUpdateModel?>

    suspend fun GetSystemDescOrderStoreLineStatusByIdExtendedAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusDTO?>

    suspend fun InsertAsync(
        model: SystemDescOrderStoreLineStatusInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescOrderStoreLineStatusUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<Unit>
}