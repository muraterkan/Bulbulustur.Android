package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBloodTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBloodTypeUpdateModel

interface ISystemDescBloodTypeRepository {

    suspend fun GetSystemDescBloodTypeListAsync(): Result<List<SystemDescBloodTypeDTO>>

    suspend fun GetSystemDescBloodTypeByIdAsync(
        systemDescBloodTypeId: Int
    ): Result<SystemDescBloodTypeUpdateModel?>

    suspend fun GetSystemDescBloodTypeByIdExtendedAsync(
        systemDescBloodTypeId: Int
    ): Result<SystemDescBloodTypeDTO?>
}
