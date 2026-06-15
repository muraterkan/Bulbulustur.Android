package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestStatusUpdateModel

interface ISystemDescReturnRequestStatusRepository {

    suspend fun GetSystemDescReturnRequestStatusListAsync(): Result<List<SystemDescReturnRequestStatusDTO>>

    suspend fun GetSystemDescReturnRequestStatusByIdAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusUpdateModel?>

    suspend fun GetSystemDescReturnRequestStatusByIdExtendedAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusDTO?>
}
