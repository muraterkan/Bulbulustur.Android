package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescHouseholdTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescHouseholdTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescHouseholdTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescHouseholdTypeRepository {

    suspend fun GetSystemDescHouseholdTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescHouseholdTypeDTO>>

    suspend fun GetSystemDescHouseholdTypeByIdAsync(
        systemDescHouseholdTypeId: Int
    ): Result<SystemDescHouseholdTypeUpdateModel?>

    suspend fun GetSystemDescHouseholdTypeByIdExtendedAsync(
        languageId: Int,
        systemDescHouseholdTypeId: Int
    ): Result<SystemDescHouseholdTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescHouseholdTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescHouseholdTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescHouseholdTypeId: Int
    ): Result<Unit>
}