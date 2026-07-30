package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVisibilityTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescVisibilityTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVisibilityTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescVisibilityTypeRepository {

    suspend fun GetSystemDescVisibilityTypesAsync(
        count: Int
    ): Result<List<SystemDescVisibilityTypeDTO>>

    suspend fun GetSystemDescVisibilityTypeByIdAsync(
        systemDescVisibilityTypeId: Int
    ): Result<SystemDescVisibilityTypeDTO?>

    suspend fun GetSystemDescVisibilityTypeByIdExtendedAsync(
        systemDescVisibilityTypeId: Int
    ): Result<SystemDescVisibilityTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescVisibilityTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescVisibilityTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescVisibilityTypeId: Int
    ): Result<Unit>
}