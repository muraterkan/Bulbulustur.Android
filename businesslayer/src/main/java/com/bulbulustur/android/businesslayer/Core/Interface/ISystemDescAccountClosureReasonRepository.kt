package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountClosureReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountClosureReasonUpdateModel

interface ISystemDescAccountClosureReasonRepository {

    suspend fun GetSystemDescAccountClosureReasonListAsync(): Result<List<SystemDescAccountClosureReasonDTO>>

    suspend fun GetSystemDescAccountClosureReasonByIdAsync(
        systemDescAccountClosureReasonId: Int
    ): Result<SystemDescAccountClosureReasonUpdateModel?>

    suspend fun GetSystemDescAccountClosureReasonByIdExtendedAsync(
        systemDescAccountClosureReasonId: Int
    ): Result<SystemDescAccountClosureReasonDTO?>
}
