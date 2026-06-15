package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescProductDenyReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescProductDenyReasonUpdateModel

interface ISystemDescProductDenyReasonRepository {

    suspend fun GetSystemDescProductDenyReasonListAsync(): Result<List<SystemDescProductDenyReasonDTO>>

    suspend fun GetSystemDescProductDenyReasonByIdAsync(
        systemDescProductDenyReasonId: Int
    ): Result<SystemDescProductDenyReasonUpdateModel?>

    suspend fun GetSystemDescProductDenyReasonByIdExtendedAsync(
        systemDescProductDenyReasonId: Int
    ): Result<SystemDescProductDenyReasonDTO?>
}
