package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountDisableReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountDisableReasonUpdateModel

interface ISystemDescAccountDisableReasonRepository {

    suspend fun GetSystemDescAccountDisableReasonListAsync(): Result<List<SystemDescAccountDisableReasonDTO>>

    suspend fun GetSystemDescAccountDisableReasonByIdAsync(
        systemDescAccountDisableReasonId: Int
    ): Result<SystemDescAccountDisableReasonUpdateModel?>

    suspend fun GetSystemDescAccountDisableReasonByIdExtendedAsync(
        systemDescAccountDisableReasonId: Int
    ): Result<SystemDescAccountDisableReasonDTO?>
}
