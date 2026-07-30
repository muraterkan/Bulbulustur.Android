package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescReturnRequestReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescReturnRequestReasonRepository {

    suspend fun GetSystemDescReturnRequestReasonsAsync(
        count: Int
    ): Result<List<SystemDescReturnRequestReasonDTO>>

    suspend fun GetSystemDescReturnRequestReasonByIdAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonDTO?>

    suspend fun GetSystemDescReturnRequestReasonByIdExtendedAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonDTO?>

    suspend fun InsertAsync(
        model: SystemDescReturnRequestReasonInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescReturnRequestReasonUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<Unit>
}