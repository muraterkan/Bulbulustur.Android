package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescReturnRequestStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescReturnRequestStatusRepository {

    suspend fun GetSystemDescReturnRequestStatusesAsync(
        count: Int
    ): Result<List<SystemDescReturnRequestStatusDTO>>

    suspend fun GetSystemDescReturnRequestStatusByIdAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusUpdateModel?>

    suspend fun GetSystemDescReturnRequestStatusByIdExtendedAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusDTO?>

    suspend fun InsertAsync(
        model: SystemDescReturnRequestStatusInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescReturnRequestStatusUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<Unit>
}