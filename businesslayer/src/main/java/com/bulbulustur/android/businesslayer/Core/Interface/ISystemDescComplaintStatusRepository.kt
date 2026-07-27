package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescComplaintStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescComplaintStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescComplaintStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescComplaintStatusRepository {

    suspend fun GetSystemDescComplaintStatusesAsync(
        count: Int
    ): Result<List<SystemDescComplaintStatusDTO>>

    suspend fun GetSystemDescComplaintStatusByIdAsync(
        systemDescComplaintStatusId: Int
    ): Result<SystemDescComplaintStatusUpdateModel?>

    suspend fun GetSystemDescComplaintStatusByIdExtendedAsync(
        systemDescComplaintStatusId: Int
    ): Result<SystemDescComplaintStatusDTO?>

    suspend fun InsertAsync(
        model: SystemDescComplaintStatusInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescComplaintStatusUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescComplaintStatusId: Int
    ): Result<Unit>
}