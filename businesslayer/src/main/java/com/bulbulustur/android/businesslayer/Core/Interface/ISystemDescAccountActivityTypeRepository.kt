package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result


import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountActivityTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountActivityTypeUpdateModel

interface ISystemDescAccountActivityTypeRepository {

    suspend fun GetSystemDescAccountActivityTypeListAsync(): Result<List<SystemDescAccountActivityTypeDTO>>

    suspend fun GetSystemDescAccountActivityTypeByIdAsync(
        systemDescAccountActivityTypeId: Int
    ): Result<SystemDescAccountActivityTypeUpdateModel?>

    suspend fun GetSystemDescAccountActivityTypeByIdExtendedAsync(
        systemDescAccountActivityTypeId: Int
    ): Result<SystemDescAccountActivityTypeDTO?>
}
