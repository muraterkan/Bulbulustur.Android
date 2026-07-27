package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLgbtqEnvironmentTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescLgbtqEnvironmentTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLgbtqEnvironmentTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescLgbtqEnvironmentTypeRepository {

    suspend fun GetSystemDescLgbtqEnvironmentTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescLgbtqEnvironmentTypeDTO>>

    suspend fun GetSystemDescLgbtqEnvironmentTypeByIdAsync(
        systemDescLgbtqEnvironmentTypeId: Int
    ): Result<SystemDescLgbtqEnvironmentTypeUpdateModel?>

    suspend fun GetSystemDescLgbtqEnvironmentTypeByIdExtendedAsync(
        languageId: Int,
        systemDescLgbtqEnvironmentTypeId: Int
    ): Result<SystemDescLgbtqEnvironmentTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescLgbtqEnvironmentTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescLgbtqEnvironmentTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescLgbtqEnvironmentTypeId: Int
    ): Result<Unit>
}