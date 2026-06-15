package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaterialTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaterialTypeUpdateModel

interface ISystemDescMaterialTypeRepository {

    suspend fun GetSystemDescMaterialTypeListAsync(): Result<List<SystemDescMaterialTypeDTO>>

    suspend fun GetSystemDescMaterialTypeByIdAsync(
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeUpdateModel?>

    suspend fun GetSystemDescMaterialTypeByIdExtendedAsync(
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeDTO?>
}
