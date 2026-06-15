package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVerificationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVerificationTypeUpdateModel

interface ISystemDescVerificationTypeRepository {

    suspend fun GetSystemDescVerificationTypeListAsync(): Result<List<SystemDescVerificationTypeDTO>>

    suspend fun GetSystemDescVerificationTypeByIdAsync(
        systemDescVerificationTypeId: Int
    ): Result<SystemDescVerificationTypeUpdateModel?>

    suspend fun GetSystemDescVerificationTypeByIdExtendedAsync(
        systemDescVerificationTypeId: Int
    ): Result<SystemDescVerificationTypeDTO?>
}
