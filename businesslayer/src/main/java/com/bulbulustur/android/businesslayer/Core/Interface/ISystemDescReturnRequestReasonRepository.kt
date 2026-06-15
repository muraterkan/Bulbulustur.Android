package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestReasonUpdateModel

interface ISystemDescReturnRequestReasonRepository {

    suspend fun GetSystemDescReturnRequestReasonListAsync(): Result<List<SystemDescReturnRequestReasonDTO>>

    suspend fun GetSystemDescReturnRequestReasonByIdAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonUpdateModel?>

    suspend fun GetSystemDescReturnRequestReasonByIdExtendedAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonDTO?>
}
