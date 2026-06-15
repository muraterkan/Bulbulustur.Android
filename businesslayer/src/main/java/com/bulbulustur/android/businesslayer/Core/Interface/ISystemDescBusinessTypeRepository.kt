package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBusinessTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBusinessTypeUpdateModel

interface ISystemDescBusinessTypeRepository {

    suspend fun GetSystemDescBusinessTypeListAsync(): Result<List<SystemDescBusinessTypeDTO>>

    suspend fun GetSystemDescBusinessTypeByIdAsync(
        systemDescBusinessTypeId: Int
    ): Result<SystemDescBusinessTypeUpdateModel?>

    suspend fun GetSystemDescBusinessTypeByIdExtendedAsync(
        systemDescBusinessTypeId: Int
    ): Result<SystemDescBusinessTypeDTO?>
}
