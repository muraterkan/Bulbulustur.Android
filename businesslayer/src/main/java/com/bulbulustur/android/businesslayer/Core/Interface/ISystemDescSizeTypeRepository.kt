package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSizeTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescSizeTypeUpdateModel

interface ISystemDescSizeTypeRepository {

    suspend fun GetSystemDescSizeTypeListAsync(): Result<List<SystemDescSizeTypeDTO>>

    suspend fun GetSystemDescSizeTypeByIdAsync(
        systemDescSizeTypeId: Int
    ): Result<SystemDescSizeTypeUpdateModel?>

    suspend fun GetSystemDescSizeTypeByIdExtendedAsync(
        systemDescSizeTypeId: Int
    ): Result<SystemDescSizeTypeDTO?>
}
