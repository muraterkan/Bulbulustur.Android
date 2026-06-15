package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaritalStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaritalStatusUpdateModel

interface ISystemDescMaritalStatusRepository {

    suspend fun GetSystemDescMaritalStatusListAsync(): Result<List<SystemDescMaritalStatusDTO>>

    suspend fun GetSystemDescMaritalStatusByIdAsync(
        systemDescMaritalStatusId: Int
    ): Result<SystemDescMaritalStatusUpdateModel?>

    suspend fun GetSystemDescMaritalStatusByIdExtendedAsync(
        systemDescMaritalStatusId: Int
    ): Result<SystemDescMaritalStatusDTO?>
}
