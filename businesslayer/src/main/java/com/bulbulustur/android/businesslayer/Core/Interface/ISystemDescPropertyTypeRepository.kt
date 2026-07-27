package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPropertyTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPropertyTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPropertyTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescPropertyTypeRepository {

    suspend fun GetSystemDescPropertyTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPropertyTypeDTO>>

    suspend fun GetSystemDescPropertyTypeByIdAsync(
        systemDescPropertyTypeId: Int
    ): Result<SystemDescPropertyTypeUpdateModel?>

    suspend fun GetSystemDescPropertyTypeByIdExtendedAsync(
        languageId: Int,
        systemDescPropertyTypeId: Int
    ): Result<SystemDescPropertyTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescPropertyTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescPropertyTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescPropertyTypeId: Int
    ): Result<Unit>
}