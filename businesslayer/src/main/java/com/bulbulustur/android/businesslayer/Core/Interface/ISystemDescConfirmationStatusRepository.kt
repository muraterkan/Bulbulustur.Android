package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescConfirmationStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescConfirmationStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescConfirmationStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescConfirmationStatusRepository {

    suspend fun GetSystemDescConfirmationStatusesAsync(
        count: Int
    ): Result<List<SystemDescConfirmationStatusDTO>>

    suspend fun GetSystemDescConfirmationStatusByIdAsync(
        confirmationStatusId: Int
    ): Result<SystemDescConfirmationStatusUpdateModel?>

    suspend fun GetSystemDescConfirmationStatusByIdExtendedAsync(
        confirmationStatusId: Int
    ): Result<SystemDescConfirmationStatusDTO?>

    suspend fun InsertAsync(
        model: SystemDescConfirmationStatusInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescConfirmationStatusUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        confirmationStatusId: Int
    ): Result<Unit>
}