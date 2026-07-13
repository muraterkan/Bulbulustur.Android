package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaterialTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMaterialTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaterialTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescMaterialTypeRepository {

    suspend fun GetSystemDescMaterialTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMaterialTypeDTO>>

    suspend fun GetSystemDescMaterialTypeByIdAsync(
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeUpdateModel?>

    suspend fun GetSystemDescMaterialTypeByIdExtendedAsync(
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescMaterialTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescMaterialTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescMaterialTypeId: Int
    ): Result<Unit>
}