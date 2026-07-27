package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescContactRequestStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescContactRequestStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescContactRequestStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescContactRequestStatusRepository {

    suspend fun GetSystemDescContactRequestStatusesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescContactRequestStatusDTO>>

    suspend fun GetSystemDescContactRequestStatusByIdAsync(
        systemDescContactRequestStatusId: Int
    ): Result<SystemDescContactRequestStatusUpdateModel?>

    suspend fun GetSystemDescContactRequestStatusByIdExtendedAsync(
        languageId: Int,
        systemDescContactRequestStatusId: Int
    ): Result<SystemDescContactRequestStatusDTO?>

    suspend fun InsertAsync(
        model: SystemDescContactRequestStatusInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescContactRequestStatusUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescContactRequestStatusId: Int
    ): Result<Unit>
}