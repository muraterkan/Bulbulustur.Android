package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMessageTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMessageTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMessageTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescMessageTypeRepository {

    suspend fun GetSystemDescMessageTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMessageTypeDTO>>

    suspend fun GetSystemDescMessageTypeByIdAsync(
        systemDescMessageTypeId: Int
    ): Result<SystemDescMessageTypeUpdateModel?>

    suspend fun GetSystemDescMessageTypeByIdExtendedAsync(
        languageId: Int,
        systemDescMessageTypeId: Int
    ): Result<SystemDescMessageTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescMessageTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescMessageTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescMessageTypeId: Int
    ): Result<Unit>
}